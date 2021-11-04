package io.github.thinkframework.cloud.client.serviceregistry;

import io.github.thinkframework.cloud.client.appinfo.Application;
import io.github.thinkframework.cloud.client.appinfo.Applications;
import io.github.thinkframework.cloud.client.commons.Lifecycle;
import io.github.thinkframework.cloud.client.discovery.DefaultServiceInstance;
import io.github.thinkframework.cloud.client.serviceregistry.util.MeasuredRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 */
public class DefaultServiceRegistry implements ServiceRegistry<DefaultServiceInstance>, ExtendServiceRegistry<DefaultServiceInstance>, Lifecycle {

    protected final Logger log = LoggerFactory.getLogger(DefaultServiceRegistry.class);

    /**
     * 线程组
     */
    protected ThreadGroup threadGroup = new ThreadGroup("thinkServiceRegistry");

    /**
     * 服务端配置
     */
    protected ServiceConfig serviceConfig;

    /**
     * 是否在运行
     */
    protected volatile boolean running;

    /**
     * 服务实例注册表
     * key: 服务名称 ->
     * key: 服务实例ID ->
     * value: 服务实例
     */
    private Map<String, Map<String, DefaultServiceInstance>> registry = new HashMap();

    private List<DefaultServiceInstance> recentChanges = new LinkedList<>();

    /**
     * 最后一分钟的请求数
     */
    private final MeasuredRate rerewsLastMin;

    public DefaultServiceRegistry() {
        rerewsLastMin = new MeasuredRate(1000 * 60 * 1); // 一分钟自动清理一次统计信息
    }

    public DefaultServiceRegistry(ServiceConfig serviceConfig) {
        this();
        this.serviceConfig = serviceConfig;
    }

    public void init(){
        Thread cleanThread = new Thread(new ClearThread());
        cleanThread.start();
    }

    @Override
    public DefaultServiceInstance register(DefaultServiceInstance registration) {
        log.debug("服务注册:{}", registration);
        registration.getLease().setRegistrationTimestamp(System.currentTimeMillis()); // 注册时间
        registration.getLease().setLastRenewalTimestamp(System.currentTimeMillis()); // 最后一次续约时间
        // 服务注册表
        Map<String, DefaultServiceInstance> services = registry
            .computeIfAbsent(registration.getServiceId(), (key) -> new HashMap<>());
        // 实例注册表
        services.computeIfAbsent(registration.getInstanceId(), (key) -> registration);

        recentChanges.add(registration); // 变更队列

        rerewsLastMin.increment(); // 注册数+1
        return registration;
    }

    @Override
    public void deregister(DefaultServiceInstance registration) {
        log.debug("服务注销:{}", registration);

        registration.getLease().setEvictionTimestamp(System.currentTimeMillis()); // 摘除时间

        // 服务注册表
        if (!registry.containsKey(registration.getServiceId())) {
            return;
        }
        // 服务注册表
        Map<String, DefaultServiceInstance> services = registry.get(registration.getServiceId());
        // 实例注册表
        services.remove(registration.getServiceId());

        recentChanges.add(registration); // 变更队列
    }

    @Override
    public void close() {
        log.debug("服务关闭.");
    }

    @Override
    public void setStatus(DefaultServiceInstance registration, String status) {

    }

    @Override
    public <T> T getStatus(DefaultServiceInstance registration) {
        return null;
    }


    @Override
    public DefaultServiceInstance renew(DefaultServiceInstance registration) {
        log.debug("服务注册:{}", registration);
        registration.getLease().setRegistrationTimestamp(System.currentTimeMillis()); // 注册时间
        registration.getLease().setLastRenewalTimestamp(System.currentTimeMillis()); // 最后一次续约时间
        // 服务注册表
        Map<String, DefaultServiceInstance> services = registry
            .computeIfAbsent(registration.getServiceId(), (key) -> new HashMap<>());
        // 实例注册表
        services.computeIfAbsent(registration.getServiceId(), (key) -> registration);

        recentChanges.add(registration); // 变更队列

        rerewsLastMin.increment(); // 续约数+1
        return registration;
    }

    /**
     * 获取所有注册表
     *
     * @return
     */
    public Applications getApplications() {
        Applications applications = new Applications(registry.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey,
                entry -> new Application(entry.getValue()))));
        return applications;
    }

    /**
     * 增量更新
     */
    public Applications getDelta() {
        Applications applications = new Applications(recentChanges.stream()
            .collect(Collectors.groupingBy(DefaultServiceInstance::getServiceId,
                Collectors.toMap(DefaultServiceInstance::getInstanceId, Function.identity())))
            .entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey,
                entry -> new Application(entry.getValue()))));
        return applications;
    }

    @Override
    public void start() {
        log.debug("服务器启动.");
    }

    @Override
    public void stop() {
        close();
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    class ClearThread implements Runnable {
        @Override
        public void run() {
            recentChanges.removeAll(recentChanges.stream().filter(defaultServiceInstance -> defaultServiceInstance.getLeaseRenewalIntervalInSeconds()
                        - System.currentTimeMillis() > defaultServiceInstance.getLeaseExpirationDurationInSeconds())
                    .collect(Collectors.toList())); // 移除所有过期的数据
        }
    }
}

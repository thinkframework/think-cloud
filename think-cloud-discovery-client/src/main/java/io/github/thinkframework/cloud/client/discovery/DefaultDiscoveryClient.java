package io.github.thinkframework.cloud.client.discovery;

import io.github.thinkframework.cloud.client.ServiceInstance;
import io.github.thinkframework.cloud.client.appinfo.Applications;
import io.github.thinkframework.cloud.client.serviceregistry.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class DefaultDiscoveryClient implements DiscoveryClient {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDiscoveryClient.class);

    /**
     * 线程组
     */
    private ThreadGroup threadGroup = new ThreadGroup("thinkDiscoveryClient");

    /**
     * 客户端配置
     */
    protected ClientConfig clientConfig;

    /**
     * 客服端实例配置
     */
    protected InstanceConfig instanceConfig;

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
    private AtomicReference<Applications> registry = new AtomicReference<>();

    /**
     * 服务注册客户端
     */
    private ServiceRegistryHttpClient client;

    private Registration registration;

    public DefaultDiscoveryClient(ClientConfig clientConfig,
                                  InstanceConfig instanceConfig,
                                  Registration registration,
                                  ServiceRegistryHttpClient client) throws RuntimeException {

        running = true; // important 核心启动逻辑,volatile保证可见性

        this.clientConfig = clientConfig;
        this.instanceConfig = instanceConfig;
        this.client = client;
        this.registration = registration;
        // 注册
        Thread register = new Thread(threadGroup, new RegisterThread());
        register.start();

        // 拉取注册表
        Thread fetch = new Thread(threadGroup, new FetchThread());
        fetch.setDaemon(true); // 守护线程
        fetch.start();


        try {
            register.join(); // 等待注册完成
        } catch (InterruptedException e){
            logger.error("注册失败",e);
            throw new RuntimeException();
        }

        // 心跳
        Thread heartBeat = new Thread(threadGroup, new HeartBeatThread());
        heartBeat.setDaemon(true); // 守护线程
        heartBeat.start();

        // 优雅关闭
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.debug("关闭客户端");
            threadGroup.interrupt();
        }));
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId){
        return registry.get().getApplication(serviceId).getInstances()
            .values().stream().collect(Collectors.toList());
    }

    @Override
    public List<String> getServices() {
        return registry.get().getApplications()
            .keySet().stream().collect(Collectors.toList());
    }

    /**
     * 注册
     */
    class RegisterThread implements Runnable {

        /**
         * 只运行一次
         */
        @Override
        public void run() {
            client.register(registration);
            logger.debug("注册完成.");
        }
    }

    /**
     * 心跳
     */
    class HeartBeatThread  implements Runnable {

        @Override
        public void run() {
            while (running) {
                client.renew(registration); // 续约
                try {
                    Thread.sleep(instanceConfig.getLeaseRenewalIntervalInSeconds() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取所有注册表
     */
    class FetchThread implements Runnable {

        @Override
        public void run() {
            while (running) {
                Applications local = null, remote = null; // 本地注册表, 远程注册表
                // CAS
                do { // TODO 逻辑不能这么简单
                    local = registry.get(); // 获取本地注册表
                    remote = client.getApplications(); // 获取远程注册表
                } while (!registry.compareAndSet(local,remote)); // 本地注册表没被修改就设置为远程注册表

                try {
                    Thread.sleep(clientConfig.getRegistryFetchIntervalSeconds() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

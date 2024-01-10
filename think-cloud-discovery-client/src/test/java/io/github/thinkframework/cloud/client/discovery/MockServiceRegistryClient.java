package io.github.thinkframework.cloud.client.discovery;

import io.github.thinkframework.cloud.client.appinfo.Application;
import io.github.thinkframework.cloud.client.appinfo.Applications;
import io.github.thinkframework.cloud.client.serviceregistry.Registration;
import io.github.thinkframework.cloud.client.serviceregistry.ServiceRegistryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 单元测试用的服务注册发现客户端
 */
public class MockServiceRegistryClient implements ServiceRegistryClient {

    private static final Logger logger = LoggerFactory.getLogger(ServiceRegistryClient.class);

    private Registration registration;

    public MockServiceRegistryClient(Registration registration) {
        this.registration = registration;
    }

    @Override
    public void register(Registration registration) {
        logger.debug("{}发起了服务注册请求",registration.getServiceId());
    }

    @Override
    public void renew(Registration registration) {
        logger.debug("{}重新发起了服务注册请求",registration.getServiceId());
    }

    @Override
    public void deregister(Registration registration) {
        logger.debug("{}发起了服务注销请求",registration.getServiceId());
    }

    @Override
    public void close() {

    }

    @Override
    public void setStatus(Registration registration, String status) {
        logger.debug("{}重新发起了服务注册请求",registration.getServiceId());
    }

    @Override
    public Object getStatus(Registration registration) {
        return null;
    }

    @Override
    public Applications getApplications() {
        logger.debug("{}发起了拉取注册表请求",registration.getServiceId());

        DefaultInstanceConfig config = new DefaultInstanceConfig();
        config.setInstanceId(UUID.randomUUID().toString());
        config.setServiceId("helloword");
        config.setHost("127.0.0.1");
        config.setPort(8080);
        config.setSecure(false);
        config.setMetadata(new HashMap<>());
        DefaultServiceInstance serviceInstance = new DefaultServiceInstance.Builder().config(config).build();

        Map<String, DefaultServiceInstance> serviceInstanceMap = new HashMap<>();
        serviceInstanceMap.put("hellowor-01",serviceInstance);
        Application application = new Application(serviceInstanceMap);

        Map<String,Application> applicationMap = new HashMap<>();
        applicationMap.put("helloword",application);
        Applications applications = new Applications(applicationMap);

        return applications;
    }

    @Override
    public Applications getDelta() {
        return getApplications();
    }

}

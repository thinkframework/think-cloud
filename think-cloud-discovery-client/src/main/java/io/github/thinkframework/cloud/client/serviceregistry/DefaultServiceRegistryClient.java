package io.github.thinkframework.cloud.client.serviceregistry;

import io.github.thinkframework.cloud.client.appinfo.Applications;
import io.github.thinkframework.cloud.client.discovery.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;

public class DefaultServiceRegistryClient implements ServiceRegistryClient {

    private static final Logger logger = LoggerFactory.getLogger(DefaultServiceRegistryClient.class);

    private static final String APPLICATIONS = "api/think/applications";

    private static final String INSTANCES = "api/think/service-instances";

    protected ClientConfig clientConfig;

    public DefaultServiceRegistryClient(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    @Override
    public void register(Registration registration) {
        logger.debug("{}发起了服务注册请求",registration.getServiceId());
        new RestTemplateBuilder().build()
                .postForObject(String.format("%s%s", clientConfig.getServiceUrl(), INSTANCES),registration,Registration.class);
    }

    @Override
    public void renew(Registration registration) {
        logger.debug("{}重新发起了服务注册请求",registration.getServiceId());
        new RestTemplateBuilder().build()
                .put(String.format("%s%s", clientConfig.getServiceUrl(), INSTANCES),registration);
    }

    @Override
    public void deregister(Registration registration) {
        logger.debug("{}发起了服务注销请求",registration.getServiceId());
        new RestTemplateBuilder().build()
                .delete(String.format("%s%s", clientConfig.getServiceUrl(), INSTANCES));
    }

    @Override
    public void close() {

    }

    @Override
    public void setStatus(Registration registration, String status) {
        logger.debug("{}重新发起了服务注册请求",registration.getServiceId());
        new RestTemplateBuilder().build()
                .put(String.format("%s%s", clientConfig.getServiceUrl(), INSTANCES),registration);
    }

    @Override
    public Object getStatus(Registration registration) {
        return null;
    }

    @Override
    public Applications getApplications() {
        ResponseEntity<Applications> response = new RestTemplateBuilder().build()
            .getForEntity(String.format("%s%s", clientConfig.getServiceUrl(), APPLICATIONS),Applications.class);
        return response.getBody();
    }

    @Override
    public Applications getDelta() {
        return null;
    }
}

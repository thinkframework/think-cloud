package io.github.thinkframework.cloud.client.discovery;

import io.github.thinkframework.cloud.client.appinfo.Applications;
import io.github.thinkframework.cloud.client.serviceregistry.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ServiceRegistryHttpClientImpl implements ServiceRegistryHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(ServiceRegistryHttpClientImpl.class);

    private static final String APPLICATIONS = "api/applications";

    protected ClientConfig clientConfig;

    public ServiceRegistryHttpClientImpl(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
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
        ResponseEntity<Applications> response = new RestTemplateBuilder().build()
            .getForEntity(String.format("%s%s", clientConfig.getServiceUrl(), APPLICATIONS),Applications.class);
        return response.getBody();
    }

    @Override
    public Applications getDelta() {
        return null;
    }
}

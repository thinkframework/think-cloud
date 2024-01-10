package io.github.thinkframework.cloud.client.serviceregistry.web.rest;

import io.github.thinkframework.cloud.client.ServiceInstance;
import io.github.thinkframework.cloud.client.appinfo.Application;
import io.github.thinkframework.cloud.client.appinfo.Applications;
import io.github.thinkframework.cloud.client.discovery.DefaultServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 服务实例集合
 */
public interface ApplicationsResource {

    /**
     * 全量
     */
    Applications getApplications();


    /**
     * 增量
     */
    Applications getDelta();


    /**
     * 应用
     */
    Application getApplication(String serviceId);

    ServiceInstance getApplication(String serviceId, String instanceId);
}

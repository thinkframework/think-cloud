package io.github.thinkframework.cloud.client.serviceregistry.web.rest.impl;

import io.github.thinkframework.cloud.client.ServiceInstance;
import io.github.thinkframework.cloud.client.appinfo.Application;
import io.github.thinkframework.cloud.client.appinfo.Applications;
import io.github.thinkframework.cloud.client.serviceregistry.DefaultServiceRegistry;
import io.github.thinkframework.cloud.client.serviceregistry.ServiceRegistry;
import io.github.thinkframework.cloud.client.serviceregistry.web.rest.ApplicationsResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api")
public class ApplicationsResourceImpl implements ApplicationsResource {

    @Resource
    private DefaultServiceRegistry registry;

    public ApplicationsResourceImpl(DefaultServiceRegistry registry) {
        this.registry = registry;
    }

    /**
     * 注册
     */
    @Override
    @GetMapping("applications")
    public Applications getApplications() {
        return registry.getApplications();
    }


    /**
     * 注册
     */
    @Override
    @GetMapping("applications-delta")
    public Applications getDelta() {
        return registry.getDelta();
    }


    @Override
    @GetMapping("applications/{serviceId}")
    public Application getApplication(@PathVariable(name = "serviceId") String serviceId) {
        return registry.getApplications()
            .getApplications().get(serviceId);
    }

    @Override
    @GetMapping("applications/{serviceId}/service-instances/{instanceId}")
    public ServiceInstance getApplication(@PathVariable(name = "serviceId") String serviceId, @PathVariable("instanceId") String instanceId) {
        return registry.getApplications()
            .getApplications().get(serviceId)
            .getInstances().get(instanceId);
    }
}

package io.github.thinkframework.cloud.client.serviceregistry.web.rest.impl;

import io.github.thinkframework.cloud.client.discovery.DefaultServiceInstance;
import io.github.thinkframework.cloud.client.serviceregistry.DefaultServiceRegistry;
import io.github.thinkframework.cloud.client.serviceregistry.web.rest.ServiceInstanceResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ServiceInstanceResourceImpl implements ServiceInstanceResource {

    @Resource
    private DefaultServiceRegistry registry;

    public ServiceInstanceResourceImpl(DefaultServiceRegistry registry) {
        this.registry = registry;
    }

    @PostMapping
    @Override
    public ResponseEntity<DefaultServiceInstance> register(@RequestBody DefaultServiceInstance serviceInstance) {
        return ResponseEntity.ok(registry.register(serviceInstance));
    }
    @DeleteMapping
    @Override
    public ResponseEntity<Void> deregister(@RequestBody DefaultServiceInstance serviceInstance) {
        registry.deregister(serviceInstance);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Override
    public ResponseEntity<DefaultServiceInstance> renew(@RequestBody DefaultServiceInstance serviceInstance) {
        return ResponseEntity.ok(registry.renew(serviceInstance));
    }

    @GetMapping("{instanceId}")
    @Override
    public ResponseEntity<DefaultServiceInstance> findById(@PathVariable("instanceId") String instanceId){
        throw new RuntimeException("未实现的接口");
    }
}

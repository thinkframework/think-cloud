package io.github.thinkframework.cloud.client.serviceregistry.web.socket.impl;

import io.github.thinkframework.cloud.client.discovery.DefaultServiceInstance;
import io.github.thinkframework.cloud.client.serviceregistry.DefaultServiceRegistry;
import io.github.thinkframework.cloud.client.serviceregistry.web.rest.ServiceInstanceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class ServiceInstanceControlerImpl implements ServiceInstanceResource {

    @Resource
    private DefaultServiceRegistry registry;

    public ServiceInstanceControlerImpl(DefaultServiceRegistry registry) {
        this.registry = registry;
    }

    @MessageMapping()
    @Override
    public ResponseEntity<DefaultServiceInstance> register(DefaultServiceInstance serviceInstance) {
        return ResponseEntity.ok(registry.register(serviceInstance));
    }

    @MessageMapping()
    @Override
    public ResponseEntity<Void> deregister(DefaultServiceInstance serviceInstance) {
        registry.deregister(serviceInstance);
        return ResponseEntity.noContent().build();
    }

    @MessageMapping()
    @Override
    public ResponseEntity<DefaultServiceInstance> renew(DefaultServiceInstance serviceInstance) {
        return ResponseEntity.ok(registry.renew(serviceInstance));
    }

    @MessageMapping()
    @Override
    public ResponseEntity<DefaultServiceInstance> findById(String instanceId){
        throw new RuntimeException("未实现的接口");
    }
}
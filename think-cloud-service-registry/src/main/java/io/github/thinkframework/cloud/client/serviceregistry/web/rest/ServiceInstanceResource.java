package io.github.thinkframework.cloud.client.serviceregistry.web.rest;

import io.github.thinkframework.cloud.client.discovery.DefaultServiceInstance;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * 服务实例资源
 * @author lixiaobin
 */
public interface ServiceInstanceResource {

    /**
     * 注册
     * @param serviceInstance 服务实例
     */

    ResponseEntity<DefaultServiceInstance> register(DefaultServiceInstance serviceInstance);

    /**
     * 注销
     * @param serviceInstance 服务实例
     */
    ResponseEntity<Void> deregister(DefaultServiceInstance serviceInstance);

    /**
     * 续约
     * @param serviceInstance 服务实例
     */

    ResponseEntity<DefaultServiceInstance> renew(DefaultServiceInstance serviceInstance);


    ResponseEntity<DefaultServiceInstance> findById(String instanceId);
}

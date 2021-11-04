package io.github.thinkframework.cloud.client.loadbalancer;

import io.github.thinkframework.cloud.client.ServiceInstance;

import java.io.IOException;
import java.net.URI;

/**
 * @see org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
 */
public interface LoadBalancerClient extends ServiceInstanceChooser {


    /**
     * Executes request using a ServiceInstance from the LoadBalancer for the specified
     * service.
     * @param serviceId The service ID to look up the LoadBalancer.
     * @param request Allows implementations to execute pre and post actions, such as
     * incrementing metrics.
     * @param <T> type of the response
     * @throws IOException in case of IO issues.
     * @return The result of the LoadBalancerRequest callback on the selected
     * ServiceInstance.
     */
    <T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException;

    /**
     * Executes request using a ServiceInstance from the LoadBalancer for the specified
     * service.
     * @param serviceId The service ID to look up the LoadBalancer.
     * @param serviceInstance The service to execute the request to.
     * @param request Allows implementations to execute pre and post actions, such as
     * incrementing metrics.
     * @param <T> type of the response
     * @throws IOException in case of IO issues.
     * @return The result of the LoadBalancerRequest callback on the selected
     * ServiceInstance.
     */
    <T> T execute(String serviceId, ServiceInstance serviceInstance,
                  LoadBalancerRequest<T> request) throws IOException;

    /**
     * Creates a proper URI with a real host and port for systems to utilize. Some systems
     * use a URI with the logical service name as the host, such as
     * http://myservice/path/to/service. This will replace the service name with the
     * host:port from the ServiceInstance.
     * @param instance service instance to reconstruct the URI
     * @param original A URI with the host as a logical service name.
     * @return A reconstructed URI.
     */
    URI reconstructURI(ServiceInstance instance, URI original);
}

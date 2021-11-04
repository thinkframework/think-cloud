package io.github.thinkframework.cloud.client.loadbalance;

import io.github.thinkframework.cloud.client.LoadBanancerApplication;
import io.github.thinkframework.cloud.client.discovery.DefaultDiscoveryClient;
import io.github.thinkframework.cloud.client.loadbalancer.DefaultLoadBalancerClient;
import io.github.thinkframework.cloud.client.loadbalancer.SpringLoadBalancerClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LoadBanancerApplication.class})
public class SpringLoadBalancerTest {

    @Autowired
    private DefaultDiscoveryClient discoveryClient;

    @Test
    public void test(){
        DefaultLoadBalancerClient defaultLoadBalancerClient = new DefaultLoadBalancerClient(discoveryClient);

        LoadBalancerClient loadBalancer = new SpringLoadBalancerClient(defaultLoadBalancerClient);

        LoadBalancerRequestFactory requestFactory = new LoadBalancerRequestFactory(loadBalancer);

        RestTemplate restTemplate = new RestTemplateBuilder()
            .additionalInterceptors()
//            .requestFactory(() -> new ThinkClientHttpRequestFactory())
            .additionalInterceptors(new LoadBalancerInterceptor(loadBalancer,requestFactory))
            .build();


        System.out.println(restTemplate.getForObject("http://word/api/helloword",String.class));
    }
}

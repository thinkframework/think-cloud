package io.github.thinkframework.cloud.client.loadbalance;

import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.web.client.RestTemplate;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {LoadBanancerApplication.class})
public class ThinkLoadBalancerTest {

    @Test
    public void test(){

        LoadBalancerClient loadBalancer = new MockLoadBalancerClient();

        LoadBalancerRequestFactory requestFactory = new LoadBalancerRequestFactory(loadBalancer);

        RestTemplate restTemplate = new RestTemplateBuilder()
            .additionalInterceptors()
//            .requestFactory(() -> new ThinkClientHttpRequestFactory())
            .additionalInterceptors(new LoadBalancerInterceptor(loadBalancer,requestFactory))
            .build();

        restTemplate.getForObject("http://word/api/word",String.class);
    }
}

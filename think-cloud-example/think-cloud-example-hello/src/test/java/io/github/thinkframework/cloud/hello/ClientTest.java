package io.github.thinkframework.cloud.hello;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ClientTest {

    public void setup(){

    }

    public void a(){
        RestTemplate restTemplate = new RestTemplateBuilder()
            .build();

        String resuult = restTemplate.getForObject("word",String.class);
        ResponseEntity response = restTemplate.getForEntity("word",String.class);
    }
}

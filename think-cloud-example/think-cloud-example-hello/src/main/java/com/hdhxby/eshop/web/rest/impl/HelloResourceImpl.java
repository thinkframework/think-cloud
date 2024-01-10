package com.hdhxby.eshop.web.rest.impl;


import com.hdhxby.eshop.web.rest.HelloResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class HelloResourceImpl implements HelloResource {

    private static final Logger logger = LoggerFactory.getLogger(HelloResourceImpl.class);

    private static final String WORD_API = "http://world/api/world";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> hello(@RequestParam(value = "name",defaultValue = "world",required = false) String name,@RequestParam(value = "millis",defaultValue = "0",required = false) Long millis){
        if (millis < 0) {
            throw new RuntimeException();
        } else if(millis > 0) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                // nothing.
            }
        }
        return ResponseEntity.ok(String.format("hello %s,sleep %d millis.", name, millis));
    }


    @Override
    public ResponseEntity<String> world(@RequestParam(value = "name",defaultValue = "world",required = false) String name, @RequestParam(value = "millis",defaultValue = "0",required = false) Long millis){
        return restTemplate.getForEntity(URI.create(String.format(WORD_API +"?name=%s&millis=%d",name,millis)),String.class);
    }
}

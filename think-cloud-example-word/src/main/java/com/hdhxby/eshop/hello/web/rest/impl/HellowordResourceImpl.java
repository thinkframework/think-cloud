package com.hdhxby.eshop.hello.web.rest.impl;

import com.hdhxby.eshop.hello.web.rest.HellowordResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HellowordResourceImpl implements HellowordResource {

    @GetMapping("/api/helloword")
    public ResponseEntity<String> hello(@RequestParam(value = "name",defaultValue = "world",required = false) String name){
        return ResponseEntity.ok(String.format("hello %s.",name));
    }
}

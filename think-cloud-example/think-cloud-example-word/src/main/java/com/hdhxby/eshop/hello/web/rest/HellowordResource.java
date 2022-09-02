package com.hdhxby.eshop.hello.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


public interface HellowordResource {

    @GetMapping("/api/helloword")
    ResponseEntity<String> hello(@RequestParam(value = "name",defaultValue = "world",required = false) String name);
}

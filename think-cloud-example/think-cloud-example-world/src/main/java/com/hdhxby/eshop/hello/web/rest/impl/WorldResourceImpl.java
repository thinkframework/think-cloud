package com.hdhxby.eshop.hello.web.rest.impl;

import com.hdhxby.eshop.hello.web.rest.WorldResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WorldResourceImpl implements WorldResource {

    @Override
    public ResponseEntity<String> world(@RequestParam(value = "name",defaultValue = "world",required = false) String name,@RequestParam(value = "millis",defaultValue = "0",required = false) Long millis){
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

}

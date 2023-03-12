package com.heaven.springdi.controllers;

import com.heaven.springdi.services.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    private final GreetingService service;

    public MyController(GreetingService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String sayHello(){
        System.out.println("I'm in the controller");
        return service.sayGreeting();
    }
}

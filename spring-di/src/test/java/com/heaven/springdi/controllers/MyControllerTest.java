package com.heaven.springdi.controllers;

import com.heaven.springdi.services.GreetingServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyControllerTest {

    @Test
    void sayHello() {
        MyController myController = new MyController(new GreetingServiceImpl());
        System.out.println(myController.sayHello());
    }
}
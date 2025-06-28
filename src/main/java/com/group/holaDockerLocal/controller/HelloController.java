package com.group.holaDockerLocal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hola")
    public String hello() {
        return "Hola AER desde Spring Boot en Docker!";
    }
}

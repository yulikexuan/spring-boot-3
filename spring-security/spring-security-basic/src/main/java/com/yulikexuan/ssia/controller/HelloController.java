package com.yulikexuan.ssia.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ssia")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

}
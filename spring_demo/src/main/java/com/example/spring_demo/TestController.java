package com.example.spring_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/test")
    public UserDto test() {

        UserDto ud = new UserDto();
        ud.setAge(20);
        ud.setName("hoon");

        return ud;
    }
}

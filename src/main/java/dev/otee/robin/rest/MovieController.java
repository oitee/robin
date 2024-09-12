package dev.otee.robin.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }
}

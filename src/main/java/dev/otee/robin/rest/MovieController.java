package dev.otee.robin.rest;

import dev.otee.robin.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @Autowired
    private MovieService service;

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }
}
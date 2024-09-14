package dev.otee.robin.rest;

import dev.otee.robin.model.Users;
import dev.otee.robin.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @Autowired
    private MovieService service;

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/add/{name}")
    public String welcome(@PathVariable String name){
        Users user = new Users(name);
        return service.addUser(user);
    }
    @GetMapping("/exists")
    public boolean exists(@RequestParam String user){
        return service.userExists(user);
    }
}

package dev.otee.robin.rest;

import dev.otee.robin.model.UserRepository;
import dev.otee.robin.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @Autowired
    UserRepository userRepository;

    @GetMapping("/add/{name}")
    public String welcome(@PathVariable String name){
        Users myUser = new Users(name);
        userRepository.save(myUser);
        return name;
    }
}

package dev.otee.robin.rest;

import dev.otee.robin.model.Users;
import dev.otee.robin.rest.dto.CreateUserResponse;
import dev.otee.robin.rest.dto.ErrorResponse;
import dev.otee.robin.rest.dto.Response;
import dev.otee.robin.rest.dto.UserExistsResponse;
import dev.otee.robin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/add/{name}")
    @ResponseBody
    public ResponseEntity<Response> createUser(@PathVariable String name){
        Users user = new Users(name);
        Optional<Users> savedUser = service.addUser(user);
        if(savedUser.isPresent()){
            return ResponseEntity.ok(new CreateUserResponse(savedUser.get().getUsername(), savedUser.get().getId()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("User already present"));
    }

    @GetMapping("/exists")
    @ResponseBody
    public ResponseEntity<UserExistsResponse> exists(@RequestParam String user){
        Boolean userExists = service.userExists(user);
        return ResponseEntity.status(HttpStatus.OK).body(new UserExistsResponse(userExists));
    }
}

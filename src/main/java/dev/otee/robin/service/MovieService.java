package dev.otee.robin.service;

import dev.otee.robin.model.UserRepository;
import dev.otee.robin.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private static final Logger log = LoggerFactory.getLogger(MovieService.class);
    @Autowired
    private UserRepository userRepository;

    public String addUser(Users user){
        userRepository.save(user);
        log.info("Added new user: {}", user.getUsername());
        return "Added New user" + user.getUsername();
    }

    public Boolean userExists(String username){
        List<Users> existingUser = userRepository.findByUsername(username);
        return !existingUser.isEmpty();
    }
}

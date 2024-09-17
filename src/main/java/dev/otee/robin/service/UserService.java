package dev.otee.robin.service;

import dev.otee.robin.model.UserRepository;
import dev.otee.robin.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(MovieService.class);
    @Autowired
    private UserRepository userRepository;

    public Optional<Users> addUser(Users user){
        if(!userRepository.findByUsername(user.getUsername()).isEmpty()){
            log.info("User with name {} already exists", user.getUsername());
            return Optional.empty();
        }
        userRepository.save(user);
        log.info("Added new user: {}", user.getUsername());
        return Optional.of(user);
    }

    public Boolean userExists(String username){
        List<Users> existingUser = userRepository.findByUsername(username);
        return !existingUser.isEmpty();
    }
}

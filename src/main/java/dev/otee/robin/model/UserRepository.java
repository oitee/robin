package dev.otee.robin.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Users, Integer> {

    List<Users> findByUsername(String username);
}
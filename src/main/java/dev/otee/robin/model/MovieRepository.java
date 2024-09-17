package dev.otee.robin.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

    List<Movie> findBySlug(String slug);
}
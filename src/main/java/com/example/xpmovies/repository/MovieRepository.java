package com.example.xpmovies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.xpmovies.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}

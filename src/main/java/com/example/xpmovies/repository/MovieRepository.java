package com.example.xpmovies.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.xpmovies.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	Page<Movie> findAllByLaunchDate(Date launchDate, Pageable pageable);

	Movie findByTitle(String title);

	Movie findByTitleAndIdNot(String title, int id);

}

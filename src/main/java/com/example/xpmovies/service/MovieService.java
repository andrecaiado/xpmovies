package com.example.xpmovies.service;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.example.xpmovies.dto.MovieCreateDto;
import com.example.xpmovies.dto.MovieUpdateDto;
import com.example.xpmovies.dto.MovieViewDto;

public interface MovieService {

	MovieViewDto createMovie(MovieCreateDto movieCreateDto);
	
	MovieViewDto getMovieById(int movieId);
	
	MovieViewDto updateMovie(MovieUpdateDto movieUpdateDto);
	
	void deleteMovie(int movieId);
	
	Page<MovieViewDto> getMovies(Date launchDate, Integer page, Integer size, String sort, String direction);
	
}

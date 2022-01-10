package com.example.xpmovies.service;

import java.util.List;

import com.example.xpmovies.dto.MovieCreateDto;
import com.example.xpmovies.dto.MovieUpdateDto;
import com.example.xpmovies.dto.MovieViewDto;

public interface MovieService {

	MovieViewDto createMovie(MovieCreateDto movieCreateDto);
	
	MovieViewDto viewMovie(int movieId);
	
	MovieViewDto updateMovie(MovieUpdateDto movieUpdateDto);
	
	void deleteMovie();
	
	List<MovieViewDto> getMoviesByLaunchDate();
	
}

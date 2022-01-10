package com.example.xpmovies.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.xpmovies.dto.MovieCreateDto;
import com.example.xpmovies.dto.MovieUpdateDto;
import com.example.xpmovies.dto.MovieViewDto;

@Service
public class MovieServiceImpl implements MovieService {

	@Override
	public MovieViewDto createMovie(MovieCreateDto movieCreateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovieViewDto viewMovie(int movieId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovieViewDto updateMovie(MovieUpdateDto movieUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMovie(int movieId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MovieViewDto> getMovies(Date launchDate) {
		// TODO Auto-generated method stub
		return null;
	}

}

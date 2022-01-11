package com.example.xpmovies.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.xpmovies.dto.MovieCreateDto;
import com.example.xpmovies.dto.MovieUpdateDto;
import com.example.xpmovies.dto.MovieViewDto;
import com.example.xpmovies.entity.Movie;
import com.example.xpmovies.exception.MovieNotFoundException;
import com.example.xpmovies.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public MovieViewDto createMovie(MovieCreateDto movieCreateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovieViewDto viewMovie(int movieId) {
		return convertMovieToMovieViewDto(getMovieById(movieId));
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
	
	private Movie getMovieById(int movieId) {
		
		Optional<Movie> movie = movieRepository.findById(movieId);
		
		if (!movie.isPresent()) {
			throw new MovieNotFoundException("The movie with id " + movieId + " was not found.");
		}
		
		return movie.get();
		
	}

	private MovieViewDto convertMovieToMovieViewDto(Movie movie) {
		
		MovieViewDto movieViewDto = new MovieViewDto();
		movieViewDto.setId(movie.getId());
		movieViewDto.setTitle(movie.getTitle());
		movieViewDto.setLaunchDate(movie.getLaunchDate());
		movieViewDto.setRank(movie.getRank());
		movieViewDto.setRevenue(movie.getRevenue());
		
		return movieViewDto;
		
	}
	
}

package com.example.xpmovies.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	public MovieViewDto getMovieById(int movieId) {
		return convertMovieToMovieViewDto(findMovieById(movieId));
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
	public Page<MovieViewDto> getMovies(Date launchDate, Integer page, Integer size, String sort, String direction) {

		Page<Movie> movies = null;		
		
		Direction sortDir = "desc".equals(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDir, sort));
		
		if (launchDate == null) {
		
			movies = movieRepository.findAll(pageable);
			
		} else {
			
			movies = movieRepository.findAllByLaunchDate(launchDate, pageable);
			
		}
		
		Page<MovieViewDto> moviesDto = movies.map( this::convertMovieToMovieViewDto );
		
		return moviesDto;
		
	}
	
	private Movie findMovieById(int movieId) {
		
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

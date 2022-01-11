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
import com.example.xpmovies.exception.MovieTitleAlreadyExistsException;
import com.example.xpmovies.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public MovieViewDto createMovie(MovieCreateDto movieCreateDto) {
		
		// Validate movie data
		validateMovieDataToCreate(movieCreateDto);
		
		Movie movie = new Movie();
		movie.setTitle(movieCreateDto.getTitle());
		movie.setLaunchDate(movieCreateDto.getLaunchDate());
		movie.setRank(movieCreateDto.getRank());
		movie.setRevenue(movieCreateDto.getRevenue());
		
		int id = movieRepository.save(movie).getId();
		
		return getMovieById(id);
	}

	private void validateMovieDataToCreate(MovieCreateDto movieCreateDto) {

		// Validate that a movie with the same title does not exists
		if (movieRepository.findByTitle(movieCreateDto.getTitle()) != null ) {
			throw new MovieTitleAlreadyExistsException("A movie with the title '" + movieCreateDto.getTitle() + "' is already registered in the database.");
		};
		
	}

	@Override
	public MovieViewDto getMovieById(int movieId) {
		return convertMovieToMovieViewDto(findMovieById(movieId));
	}

	@Override
	public MovieViewDto updateMovie(MovieUpdateDto movieUpdateDto) {
		
		Movie movie = findMovieById(movieUpdateDto.getId());
		
		// Validate movie data
		validateMovieDataToUpdate(movieUpdateDto);
				
		movie.setTitle(movieUpdateDto.getTitle());
		movie.setLaunchDate(movieUpdateDto.getLaunchDate());
		movie.setRank(movieUpdateDto.getRank());
		movie.setRevenue(movieUpdateDto.getRevenue());
		
		int id = movieRepository.save(movie).getId();
		
		return getMovieById(id);
		
	}
	
	private void validateMovieDataToUpdate(MovieUpdateDto movieUpdateDto) {

		// Validate that a movie with the same title does not exists (besides the movie we are updating)
		if (movieRepository.findByTitleAndIdNot(movieUpdateDto.getTitle(), movieUpdateDto.getId()) != null ) {
			throw new MovieTitleAlreadyExistsException("A movie with the title '" + movieUpdateDto.getTitle() + "' is already registered in the database.");
		};
		
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

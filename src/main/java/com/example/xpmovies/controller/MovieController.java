package com.example.xpmovies.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.xpmovies.dto.MovieCreateDto;
import com.example.xpmovies.dto.MovieUpdateDto;
import com.example.xpmovies.dto.MovieViewDto;
import com.example.xpmovies.service.MovieService;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping()
	public ResponseEntity<List<MovieViewDto>> getMoviesByLaunchDate(
			@RequestParam("launchdate") @DateTimeFormat(pattern="yyyy-MM-dd") Date launchDate) {
		return ResponseEntity.ok(movieService.getMovies(launchDate));
	}
	
	@GetMapping("/{movieId}")
	public ResponseEntity<MovieViewDto> getMoviesById(@PathVariable("movieId") int movieId) {
		return ResponseEntity.ok(movieService.viewMovie(movieId));
	}
	
	@PostMapping()
	public ResponseEntity<MovieViewDto> createMovie(@Valid @RequestBody MovieCreateDto movieCreateDto) {
		return ResponseEntity.ok(movieService.createMovie(movieCreateDto));
	}
	
	@PutMapping()
	public ResponseEntity<MovieViewDto> updateMovie(@Valid @RequestBody MovieUpdateDto movieUpdateDto) {
		return ResponseEntity.ok(movieService.updateMovie(movieUpdateDto));
	}
	
	@DeleteMapping("/{movieId}")
	public void deleteMovie(@PathVariable("movieId") int movieId) {
		movieService.deleteMovie(movieId);
		return;
	}
	
}

package com.example.xpmovies.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	public ResponseEntity<Page<MovieViewDto>> getMoviesByLaunchDate(
			@RequestParam(value="launchdate", required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date launchDate,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="size", defaultValue="10") Integer size,
			@RequestParam(value="sort", defaultValue="title") String sort,
			@RequestParam(value="direction", defaultValue="asc") String direction) {
		return ResponseEntity.ok(movieService.getMovies(launchDate, page, size, sort, direction));
	}
	
	@GetMapping("/{movieId}")
	public ResponseEntity<MovieViewDto> getMovieById(@PathVariable("movieId") int movieId) {
		return ResponseEntity.ok(movieService.getMovieById(movieId));
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

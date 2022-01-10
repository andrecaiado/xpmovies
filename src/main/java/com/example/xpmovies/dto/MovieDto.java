package com.example.xpmovies.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieDto {

	private String title;
	private Date launchDate;
	private int rank;
	private Double revenue;
	
}

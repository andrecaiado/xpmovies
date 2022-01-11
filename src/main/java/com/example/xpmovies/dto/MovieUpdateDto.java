package com.example.xpmovies.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieUpdateDto {

	private int id;
	private String title;
	private Date launchDate;
	private Double rank;
	private BigDecimal revenue;
	
}

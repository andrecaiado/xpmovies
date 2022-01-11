package com.example.xpmovies.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieViewDto {

	private int id;
	private String title;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Europe/Lisbon")
	private Date launchDate;
	private Double rank;
	private BigDecimal revenue;
	
}

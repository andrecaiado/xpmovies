package com.example.xpmovies.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieCreateDto {

	@NotBlank(message="Please supply the title.")
	private String title;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Europe/Lisbon")
	@NotNull(message="Please supply the launch date (format is yyyy-MM-dd).")
	private Date launchDate;
	
	@NotNull(message="Please supply the rank.")
	@Min(value=0, message="The rank cannot be less than 0")
	@Max(value=10, message="The rank cannot be greater than 10") 
	private Double rank;
	
	@NotNull(message="Please supply the revenue.")
	private BigDecimal revenue;
	
}

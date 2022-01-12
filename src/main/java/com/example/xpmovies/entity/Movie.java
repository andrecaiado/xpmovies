package com.example.xpmovies.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name="movie")
public class Movie {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Europe/Lisbon")
	@Column(name="launch_date")
	private Date launchDate;
	
	@Column(name="rank")
	private Double rank;
	
	@Column(name="revenue")
	private BigDecimal revenue;
	
}

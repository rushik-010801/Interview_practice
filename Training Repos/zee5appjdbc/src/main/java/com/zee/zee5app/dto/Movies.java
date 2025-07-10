package com.zee.zee5app.dto;

import javax.naming.NameNotFoundException;

import com.zee.zee5app.exception.LocationNotFound;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movies {
	
	@Setter(value = AccessLevel.NONE)
	private String movieName;
	private String category;
	private String releaseDate;
	
	@Setter(value = AccessLevel.NONE)
	private String trailer;
	private String language;
	private String cast;
	private int length;
	private String id;
	private int agelimit;

	public void setMovieName(String movieName) throws NameNotFoundException {
		if(movieName == null || movieName.length() == 0) {
			throw new NameNotFoundException("Movie Name is not found");
		}
		this.movieName = movieName;
	}
	public void setTrailer(String trailer) throws LocationNotFound {
		if(trailer == null || trailer.length() == 0) {
			throw new LocationNotFound("Trailer Location not found");
		}
		this.trailer = trailer;
	}
}

package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movies;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.LocationNotFound;
import com.zee.zee5app.exception.NameNotFoundException;

public interface MovieService {
	public String addMovie(Movies movie);
	public Optional<List<Movies>> getMovies() throws javax.naming.NameNotFoundException, LocationNotFound;
	public Optional<Movies> getMovieById(String id) throws IdNotFoundException, javax.naming.NameNotFoundException, LocationNotFound;
	public String deleteMovieById(String id) throws IdNotFoundException;
	public String modifyMovieById(String id, Movies movie);
}

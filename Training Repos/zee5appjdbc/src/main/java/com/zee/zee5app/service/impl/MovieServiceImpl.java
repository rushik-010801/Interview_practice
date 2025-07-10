package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Movies;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.LocationNotFound;
import com.zee.zee5app.exception.NameNotFoundException;
import com.zee.zee5app.repository.impl.MovieRepositoryImpl;
import com.zee.zee5app.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{
private MovieRepositoryImpl repository;
	
	public MovieServiceImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String addMovie(Movies movie) {
		return this.repository.addMovie(movie);
	}
	
	@Override
	public Optional<Movies> getMovieById(String id) throws IdNotFoundException, javax.naming.NameNotFoundException, LocationNotFound {
		return this.repository.getMovieById(id);
	}
	
	@Override
	public Optional<List<Movies>> getMovies() throws javax.naming.NameNotFoundException, LocationNotFound {
		return repository.getMovies();
	}
	
	@Override
	public String modifyMovieById(String id, Movies movie) {
		return repository.modifyMovieById(id, movie);
	}
	
	@Override
	public String deleteMovieById(String id) throws IdNotFoundException {
		return repository.deleteMovieById(id);
	}
}

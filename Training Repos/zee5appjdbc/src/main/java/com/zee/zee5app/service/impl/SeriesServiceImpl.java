package com.zee.zee5app.service.impl;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.impl.SeriesRepositoryImpl;
import com.zee.zee5app.service.SeriesService;

@Service
public class SeriesServiceImpl implements SeriesService{
private SeriesRepositoryImpl repository;
	
	public SeriesServiceImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String addSeries(Series serie) {
		return this.repository.addSeries(serie);
	}
	
	@Override
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException, NameNotFoundException {
		return this.repository.getSeriesById(id);
	}
	
	@Override
	public Optional<List<Series>> getSeries() throws NameNotFoundException {
		return repository.getSeries();
	}
	
	@Override
	public String modifySeriesById(String id, Series serie) {
		return repository.modifySeriesById(id, serie);
	}
	
	@Override
	public String deleteSeriesById(String id) throws IdNotFoundException {
		return repository.deleteSeriesById(id);
	}
}

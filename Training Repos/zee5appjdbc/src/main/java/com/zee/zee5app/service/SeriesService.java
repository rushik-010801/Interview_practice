package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;

public interface SeriesService {
	public String addSeries(Series serie);
	public Optional<List<Series>> getSeries() throws NameNotFoundException;
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException, NameNotFoundException;
	public String deleteSeriesById(String id) throws IdNotFoundException;
	public String modifySeriesById(String id, Series serie);
}

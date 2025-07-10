package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Episodes;

public interface EpisodeService {
	public String addEpisode(Episodes episode);
	public Optional<List<Episodes>> getEpisodes();
	public Optional<Episodes> getEpisodeById(String id);
	public String deleteEpisodeById(String id);
	public String modifyEpisodeById(String id, Episodes episode);
}

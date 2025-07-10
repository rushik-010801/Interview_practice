package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Episodes;
import com.zee.zee5app.repository.EpisodeRepository;
import com.zee.zee5app.repository.impl.EpisodeRepositoryImpl;
import com.zee.zee5app.service.EpisodeService;
import com.zee.zee5app.utils.DBUtils;


@Service
public class EpisodeServiceImpl implements EpisodeService {
		
	private EpisodeRepository repository;
	
	public EpisodeServiceImpl() throws IOException{
		
	}
	
	@Override
	public String addEpisode(Episodes episode) {
		// TODO Auto-generated method stub
		return repository.addEpisode(episode);
	}

	@Override
	public Optional<List<Episodes>> getEpisodes() {
		// TODO Auto-generated method stub
		return this.repository.getEpisodes();
	}

	@Override
	public Optional<Episodes> getEpisodeById(String id) {
		// TODO Auto-generated method stub
		return repository.getEpisodeById(id);
	}

	@Override
	public String deleteEpisodeById(String id) {
		// TODO Auto-generated method stub
		return repository.deleteEpisodeById(id);
	}

	@Override
	public String modifyEpisodeById(String id, Episodes episode) {
		// TODO Auto-generated method stub
		return repository.modifyEpisodeById(id, episode);
	}

}

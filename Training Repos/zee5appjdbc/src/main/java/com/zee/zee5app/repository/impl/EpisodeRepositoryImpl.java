package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Episodes;
import com.zee.zee5app.dto.Subscriptions;
import com.zee.zee5app.dto.Episodes;
import com.zee.zee5app.repository.EpisodeRepository;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class EpisodeRepositoryImpl implements EpisodeRepository {
	
	@Autowired
	DataSource datasource;
	//DBUtils dbutils = DBUtils.getInstance();
	
	public EpisodeRepositoryImpl() throws IOException{
		
	}
//	private static EpisodeRepository repo = null;
//	public static EpisodeRepository getInstance() throws IOException {
//		if (repo == null) {
//			repo = new EpisodeRepositoryImpl();
//		}
//		return repo;
//	}
	@Override
	public String addEpisode(Episodes episode) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//insert statement
		String inputstatement = "insert into episodes(epiId, serId, episodename, epilength, location) values"
				+ "(?,?,?,?,?)";
		
		//preparing statement
		try {
			connection = datasource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			preparedstatement = connection.prepareStatement(inputstatement);
			preparedstatement.setString(1, episode.getEpiId());
			preparedstatement.setString(2, episode.getSerId());
			preparedstatement.setString(3, episode.getEpisodename());
			preparedstatement.setFloat(4, episode.getEpilength());
			preparedstatement.setString(5, episode.getLocation());
			
			int result = preparedstatement.executeUpdate();
			if(result > 0) {
				connection.commit();
				return "success";
			}
			else {
				connection.rollback();
				return "fail";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "fail";
		}
		
	}

	@Override
	public Optional<List<Episodes>> getEpisodes() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedstatment = null;
		ResultSet resultset = null;
		List<Episodes> arrayList = new ArrayList<>();
		
		//select query
		try {
			connection = datasource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "select * from episodes";
		try {
			preparedstatment = connection.prepareStatement(query);			
			resultset = preparedstatment.executeQuery();
			
			while(resultset.next()) {
				Episodes episode = new Episodes();
				episode.setEpiId(resultset.getString("epiId"));
				episode.setSerId(resultset.getString("serId"));
				episode.setEpisodename(resultset.getString("episodename"));
				episode.setEpilength(resultset.getFloat("epilength"));
				episode.setLocation(resultset.getString("location"));
				arrayList.add(episode);
			}
			return Optional.ofNullable(arrayList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<Episodes> getEpisodeById(String id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedstatment = null;
		ResultSet resultset = null;
		
		//select query
		try {
			connection = datasource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "select * from episodes where epiId = ?";
		try {
			preparedstatment = connection.prepareStatement(query);
			preparedstatment.setString(1, id);
			
			resultset = preparedstatment.executeQuery();
			
			if(resultset.next()) {
				Episodes episode = new Episodes();
				episode.setEpiId(resultset.getString("epiId"));
				episode.setSerId(resultset.getString("serId"));
				episode.setEpisodename(resultset.getString("episodename"));
				episode.setEpilength(resultset.getFloat("epilength"));
				episode.setLocation(resultset.getString("location"));
				return Optional.of(episode);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String deleteEpisodeById(String id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//delete statement
		String deletestatement = "delete from episodes where epiId = ?";
		
		//preparing statement
		try {
			connection = datasource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			preparedstatement = connection.prepareStatement(deletestatement);
			preparedstatement.setString(1, id);
			
			int result = preparedstatement.executeUpdate();
			if(result > 0) {
				//deleting in Login table
				
				return "success";
			}
			else {
				return "fail";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return "fail";
		}
		
	}

	@Override
	public String modifyEpisodeById(String id, Episodes episode) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//update statement
		String updatestatement = "update episodes set serId = ?, episodename = ?, epilength = ?, location = ? where epiId = ?";
		
		//preparing statement
		try {
			connection = datasource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			preparedstatement = connection.prepareStatement(updatestatement);
			preparedstatement.setString(5, episode.getEpiId());
			preparedstatement.setString(1, episode.getSerId());
			preparedstatement.setString(2, episode.getEpisodename());
			preparedstatement.setFloat(3, episode.getEpilength());
			preparedstatement.setString(4, episode.getLocation());

			int result = preparedstatement.executeUpdate();
			if(result > 0) {
				connection.commit();
				return "success";
			}
			else {
				connection.rollback();
				return "fail";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "fail";
		}
	}

}

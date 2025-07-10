package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import javax.naming.NameNotFoundException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Movies;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.dto.Subscriptions;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class SeriesRepositoryImpl implements SeriesRepository{
	
	@Autowired
	DataSource datasource;
	//DBUtils dbutils = DBUtils.getInstance();
	
	public SeriesRepositoryImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String addSeries(Series serie) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//insert statement
		String inputstatement = "insert into series"
				+ "(serId, agelimit, cast, category, length, releasedate, language, noofepisodes, seriename) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		
		//preparing statement
		try {
			connection = datasource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			preparedstatement = connection.prepareStatement(inputstatement);
			preparedstatement.setString(1, serie.getId());
			preparedstatement.setInt(2, serie.getAgelimit());
			preparedstatement.setString(3, serie.getCast());
			preparedstatement.setString(4, serie.getCategory());
			preparedstatement.setInt(5, serie.getLength());
			preparedstatement.setString(6, serie.getReleasedate());
			preparedstatement.setString(7, serie.getLanguage());
			preparedstatement.setInt(8, serie.getNoofepisodes());
			preparedstatement.setString(9, serie.getSeriesName());

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
	public Optional<List<Series>> getSeries() throws NameNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedstatment = null;
		ResultSet resultset = null;
		List<Series> arrayList = new ArrayList<>();
		
		//select query
		try {
			connection = datasource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "select * from series";
		try {
			preparedstatment = connection.prepareStatement(query);			
			resultset = preparedstatment.executeQuery();
			
			while(resultset.next()) {
				Series series = new Series();
				series.setId(resultset.getString("serid"));
				series.setAgelimit(resultset.getInt("agelimit"));
				series.setCast(resultset.getString("cast"));
				series.setCategory(resultset.getString("category"));
				series.setLength(resultset.getInt("length"));
				series.setReleasedate(resultset.getString("releasedate"));
				series.setLanguage(resultset.getString("language"));
				series.setNoofepisodes(resultset.getInt("noofepisodes"));
				series.setSeriesName(resultset.getString("seriename"));	
				arrayList.add(series);
			}
			return Optional.ofNullable(arrayList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Optional.empty();
	}
	@Override
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException, NameNotFoundException {
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
		String query = "select * from series where serid = ?";
		try {
			preparedstatment = connection.prepareStatement(query);
			preparedstatment.setString(1, id);
			
			resultset = preparedstatment.executeQuery();
			
			if(resultset.next()) {
				Series series = new Series();
				series.setId(resultset.getString("serid"));
				series.setAgelimit(resultset.getInt("agelimit"));
				series.setCast(resultset.getString("cast"));
				series.setCategory(resultset.getString("category"));
				series.setLength(resultset.getInt("length"));
				series.setReleasedate(resultset.getString("releasedate"));
				series.setLanguage(resultset.getString("language"));
				series.setNoofepisodes(resultset.getInt("noofepisodes"));
				series.setSeriesName(resultset.getString("seriename"));	
				return Optional.of(series);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String deleteSeriesById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//delete statement
		String deletestatement = "delete from series where serid = ?";
		
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
				//deleting in Series table
				
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
	public String modifySeriesById(String id, Series serie) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//update statement
		String updatestatement = "update series set agelimit = ?, cast = ?, category = ?, length = ?, releasedate = ?, "
				+ "language = ?, noofepisodes = ?, seriename = ? where serid = ?";
		
		//preparing statement
		try {
			connection = datasource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			preparedstatement = connection.prepareStatement(updatestatement);
			preparedstatement.setString(9, serie.getId());
			preparedstatement.setInt(1, serie.getAgelimit());
			preparedstatement.setString(2, serie.getCast());
			preparedstatement.setString(3, serie.getCategory());
			preparedstatement.setInt(4, serie.getLength());
			preparedstatement.setString(5, serie.getReleasedate());
			preparedstatement.setString(6, serie.getLanguage());
			preparedstatement.setInt(7, serie.getNoofepisodes());
			preparedstatement.setString(8, serie.getSeriesName());

			
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

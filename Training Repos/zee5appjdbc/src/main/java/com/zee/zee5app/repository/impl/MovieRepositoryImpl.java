package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.NameNotFoundException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.Movies;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.LocationNotFound;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.utils.DBUtils;
import com.zee.zee5app.utils.PasswordUtils;

@Repository
public class MovieRepositoryImpl implements MovieRepository{
	
	@Autowired
	DataSource datasource;
	//DBUtils dbutils = DBUtils.getInstance();
	
	public MovieRepositoryImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String addMovie(Movies movie) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//insert statement
		String inputstatement = "insert into movies"
				+ "(movId, moviename, category, releasedate, trailer, length, language, cast, agelimit) "
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
			preparedstatement.setString(1, movie.getId());
			preparedstatement.setString(2, movie.getMovieName());
			preparedstatement.setString(3, movie.getCategory());
			preparedstatement.setString(4, movie.getReleaseDate());
			preparedstatement.setString(5, movie.getTrailer());
			preparedstatement.setInt(6, movie.getLength());
			preparedstatement.setString(7, movie.getLanguage());
			preparedstatement.setString(8, movie.getCast());
			preparedstatement.setInt(9, movie.getAgelimit());

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
	public Optional<List<Movies>> getMovies() throws NameNotFoundException, LocationNotFound {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedstatment = null;
		ResultSet resultset = null;
		List<Movies> arrayList = new ArrayList<>();
		
		//select query
		try {
			connection = datasource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "select * from movies";
		try {
			preparedstatment = connection.prepareStatement(query);			
			resultset = preparedstatment.executeQuery();
			
			while(resultset.next()) {
				Movies movie = new Movies();
				movie.setId(resultset.getString("movId"));
				movie.setMovieName(resultset.getString("moviename"));
				movie.setCategory(resultset.getString("category"));
				movie.setLanguage(resultset.getString("language"));
				movie.setReleaseDate(resultset.getString("releasedate"));
				movie.setTrailer(resultset.getString("trailer"));
				movie.setAgelimit(resultset.getInt("agelimit"));
				movie.setLength(resultset.getInt("length"));
				movie.setCast(resultset.getString("cast"));
				arrayList.add(movie);
			}
			return Optional.ofNullable(arrayList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}
	@Override
	public Optional<Movies> getMovieById(String id) throws IdNotFoundException, NameNotFoundException, LocationNotFound{
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
		String query = "select * from movies where movId = ?";
		try {
			preparedstatment = connection.prepareStatement(query);
			preparedstatment.setString(1, id);
			
			resultset = preparedstatment.executeQuery();
			
			if(resultset.next()) {
				Movies movie = new Movies();
				movie.setId(resultset.getString("movId"));
				movie.setMovieName(resultset.getString("moviename"));
				movie.setCategory(resultset.getString("category"));
				movie.setLanguage(resultset.getString("language"));
				movie.setReleaseDate(resultset.getString("releasedate"));
				movie.setTrailer(resultset.getString("trailer"));
				movie.setAgelimit(resultset.getInt("agelimit"));
				movie.setLength(resultset.getInt("length"));
				movie.setCast(resultset.getString("cast"));
				return Optional.of(movie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String deleteMovieById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//delete statement
		String deletestatement = "delete from movies where movId = ?";
		
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
	public String modifyMovieById(String id, Movies movie) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//update statement
		String updatestatement = "update movies set moviename = ?, category = ?, releasedate = ?, trailer = ?, length = ?, language = ?, cast = ?, agelimit = ? where movId = ?";
		
		//preparing statement
		try {
			connection = datasource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			preparedstatement = connection.prepareStatement(updatestatement);
			preparedstatement.setString(9, movie.getId());
			preparedstatement.setString(1, movie.getMovieName());
			preparedstatement.setString(2, movie.getCategory());
			preparedstatement.setString(3, movie.getReleaseDate());
			preparedstatement.setString(4, movie.getTrailer());
			preparedstatement.setInt(5, movie.getLength());
			preparedstatement.setString(6, movie.getLanguage());
			preparedstatement.setString(7, movie.getCast());
			preparedstatement.setInt(8, movie.getAgelimit());
			
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

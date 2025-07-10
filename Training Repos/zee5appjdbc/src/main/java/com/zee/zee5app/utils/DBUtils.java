package com.zee.zee5app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class DBUtils {
	//DButils is used for creating DB Connection and also for closing.
	//Singleton design pattern
	
	Properties properties;
	Connection connection = null;
	
	public DBUtils() throws IOException {
		properties = loadProperties();
	}
	public static DBUtils dbutils = null;
	public static DBUtils getInstance() throws IOException {
		if(dbutils == null)
			dbutils = new DBUtils();
		return dbutils;
	}
	public Connection getConnection() {
		//Connection to Database
		try {
		if(connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(
						properties.getProperty("jdbc.url"),
						properties.getProperty("jdbc.username"),
						properties.getProperty("jdbc.password"));
			System.out.println(properties);
			connection.setAutoCommit(false);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return connection;
	}
	private Properties loadProperties() throws IOException {
		// read the properties file
		InputStream inputstream = DBUtils.class.getClassLoader()
				.getResourceAsStream("application.properties");
		Properties properties = new Properties();
		properties.load(inputstream);
		//it will read properties internally
		
		return properties;
	}
	
	public void closeConnection(Connection connection) {
		//for closing the connection
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]) throws IOException {
		DBUtils dbutils = getInstance();
		Connection connection = dbutils.getConnection();
		System.out.println(connection!= null);
//		dbutils.closeConnection(connection);
//		System.out.println(connection);
	}
}

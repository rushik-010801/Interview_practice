package com.zee.zee5app.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import com.zee.zee5app.utils.PasswordUtils;

@Configuration // Used to mark config classes/class

@ComponentScan("com.zee.zee5app")
@PropertySource("classpath:application.properties")
public class Config {
	//config : DB related, reading prop file commonly used beans(passwordencoders)
	@Autowired // bring all created objs by their name / type
	Environment environment;
	
	@Bean(name = "ds") // for providing singleton object
	//@Scope("prototype") // creates multiple instances if prototype
	@Scope("singleton") // for singleton object
	public DataSource dataSource() {
		BasicDataSource basicdatasource = new BasicDataSource();
		basicdatasource.setUsername(environment.getProperty("jdbc.username"));
		basicdatasource.setPassword(environment.getProperty("jdbc.password"));
		basicdatasource.setUrl(environment.getProperty("jdbc.url"));
		basicdatasource.setDefaultAutoCommit(false);
		
		return basicdatasource;
	}
	
	@Bean
	public PasswordUtils passwordutils() {
		return new PasswordUtils();
	}
}

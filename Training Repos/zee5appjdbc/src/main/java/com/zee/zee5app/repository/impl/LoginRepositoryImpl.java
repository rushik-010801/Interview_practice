package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.utils.DBUtils;
import com.zee.zee5app.utils.PasswordUtils;

@Repository
public class LoginRepositoryImpl implements LoginRepository {

	@Autowired
	DataSource datasource;
	//DBUtils dbutils = DBUtils.getInstance();
	
	public LoginRepositoryImpl() throws IOException{
		
	}
	
	@Override
	public String addCredentials(Login login) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//insert statement
		String inputstatement = "insert into login"
						+ "(username, password, regId, role)"
						+ "values(?,?,?,?)";
		
		//preparing statement
		try {
			connection = datasource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			preparedstatement = connection.prepareStatement(inputstatement);
			preparedstatement.setString(1, login.getUserName());
			preparedstatement.setString(2, login.getPassword());
			preparedstatement.setString(3, login.getRegId());
			preparedstatement.setString(4, login.getRole().toString());
			
			int result = preparedstatement.executeUpdate();
			if(result > 0) {
				//connection.commit();
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
	public String deleteCredentials(String userName) {
		// TODO Auto-generated method stub
		// No need to implement this one because when register record is
		//deleted automatically login record is deleted because it is cascade  
		//option in database
		return null;
	}

	@Override
	public String changePassword(String userName, String password) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updatestatement = "update login set password = ? where username = ?";
		try {
			connection = datasource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try{
			preparedStatement = connection.prepareStatement(updatestatement);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, userName);
			
			int result = preparedStatement.executeUpdate();
			if(result > 0) {
				connection.commit();
				return "success";
			}
			else {
				connection.rollback();
				return "fail";
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.getStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "fail";
		}
		
		
	}
	
	@Override
	public String changeRole(String username, ROLE role) {
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		
		try {
			connection = datasource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String updatestatement = "update login set role = ? where username = ?";
		try {
			preparedstatement = connection.prepareStatement(updatestatement);
			preparedstatement.setString(1, role.toString());
			preparedstatement.setString(2, username);
			
			int result = preparedstatement.executeUpdate();
			if(result > 0) {
				connection.commit();
				return "success";
			}
			else {
				connection.rollback();
				return "fail";
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.getStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "fail";
		}
		
		
	}
	
}

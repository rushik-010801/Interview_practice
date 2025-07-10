package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.utils.DBUtils;
import com.zee.zee5app.utils.PasswordUtils;

@Repository // create singleton object for us
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	DataSource datasource;
	//DBUtils dbutils = DBUtils.getInstance();
	@Autowired
	LoginRepository loginrepository;
	@Autowired
	PasswordUtils passwordutils;
	
	public UserRepositoryImpl() throws IOException{
		
	}
	
	
	@Override
	public String addUser(Register register) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//insert statement
		String inputstatement = "insert into register"
				+ "(regId, firstname, lastname, email, contactnumber, password) "
				+ "values(?,?,?,?,?,?)";
		
		//preparing statement
		try {
			connection = datasource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			preparedstatement = connection.prepareStatement(inputstatement);
			preparedstatement.setString(1, register.getId());
			preparedstatement.setString(2, register.getFirstName());
			preparedstatement.setString(3, register.getLastName());
			preparedstatement.setString(4, register.getEmail());
			preparedstatement.setBigDecimal(5, register.getContactNumber());
			String salt = passwordutils.getSalt(30);
			String encryptedpassword = passwordutils.generateSecurePassword(register.getPassword(), salt);
			preparedstatement.setString(6, encryptedpassword);
			
			int result = preparedstatement.executeUpdate();
			if(result > 0) {
				//connection.commit();
				Login login = new Login();
				login.setUserName(register.getEmail());
				login.setPassword(encryptedpassword);
				login.setRegId(register.getId());
				login.setRole(ROLE.ROLE_USER);
				String res = loginrepository.addCredentials(login);
				if(res.equals("success")) {
					
					return "success";
				}
				else {
					connection.rollback();
					return "fail";
				}
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
	public String updateUser(String id, Register register) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//update statement
		String updatestatement = "update register set firstname = ?, lastname = ?, contactnumber = ?, password = ? where regId = ?";
		
		//preparing statement
		try {
			connection = datasource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			preparedstatement = connection.prepareStatement(updatestatement);
			preparedstatement.setString(1, register.getFirstName());
			preparedstatement.setString(2, register.getLastName());
			preparedstatement.setBigDecimal(3, register.getContactNumber());
			
			String salt = passwordutils.getSalt(30);
			String encryptedpassword = passwordutils.generateSecurePassword(register.getPassword(), salt);
			preparedstatement.setString(4, encryptedpassword);
			preparedstatement.setString(5, register.getId());
			
			int result = preparedstatement.executeUpdate();
			if(result > 0) {
				String res = loginrepository.changePassword(register.getEmail(), encryptedpassword);
				if(res.equals("success")) {
					return "success";
				}
				else {
					connection.rollback();
					return "fail";
				}
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
	public Optional<Register> getUserById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException, InvalidEmailException, InvalidPasswordException {
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
		String query = "select * from register where regId = ?";
		try {
			preparedstatment = connection.prepareStatement(query);
			preparedstatment.setString(1, id);
			
			resultset = preparedstatment.executeQuery();
			
			while(resultset.next()) {
				Register register = new Register();
				register.setId(resultset.getString("regId"));
				register.setFirstName(resultset.getString("firstname"));
				register.setLastName(resultset.getString("lastname"));
				register.setEmail(resultset.getString("email"));
				register.setContactNumber(resultset.getBigDecimal("contactnumber"));
				register.setPassword(resultset.getString("password"));
				return Optional.of(register);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Register[] getAllUsers() throws InvalidNameException, InvalidIdLengthException, InvalidEmailException, InvalidPasswordException {
		// TODO Auto-generated method stub
		Optional<List<Register>> optional = getAllUserDetails();
		if(optional.isEmpty()) {
			return null;
		}
		else {
			List<Register> arraylist = optional.get(); 
			Register[] registers = new Register[arraylist.size()];
			return arraylist.toArray(registers);
		}
	}
	@Override
	public Optional<List<Register>> getAllUserDetails() throws InvalidIdLengthException, InvalidNameException, InvalidEmailException, InvalidPasswordException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedstatment = null;
		ResultSet resultset = null;
		List<Register> arrayList = new ArrayList<>();
		
		//select query
		try {
			connection = datasource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "select * from register";
		try {
			preparedstatment = connection.prepareStatement(query);			
			resultset = preparedstatment.executeQuery();
			
			while(resultset.next()) {
				Register register = new Register();
				register.setId(resultset.getString("regId"));
				register.setFirstName(resultset.getString("firstname"));
				register.setLastName(resultset.getString("lastname"));
				register.setEmail(resultset.getString("email"));
				register.setContactNumber(resultset.getBigDecimal("contactnumber"));
				register.setPassword(resultset.getString("password"));
				arrayList.add(register);
			}
			return Optional.ofNullable(arrayList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}
	@Override
	public String deleteUserById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//delete statement
		String deletestatement = "delete from register where regId = ?";
		
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
	
	
}

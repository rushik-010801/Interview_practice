package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.repository.impl.UserRepositoryImpl;
import com.zee.zee5app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	UserRepository userrepository;
	
	@Override
	public String addUser(Register register) {
		return userrepository.addUser(register);
	}

	@Override
	public String updateUser(String id, Register register) {
		return userrepository.updateUser(id, register);
	}

	@Override
	public Optional<Register> getUserById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException, InvalidEmailException, InvalidPasswordException{
		return this.userrepository.getUserById(id);
	}

	@Override
	public Register[] getAllUsers() throws InvalidNameException, InvalidIdLengthException, InvalidEmailException, InvalidPasswordException  {
		return userrepository.getAllUsers();
	}

	@Override
	public String deleteUserById(String id) throws IdNotFoundException {
		return userrepository.deleteUserById(id);
	}

	@Override
	public Optional<List<Register>> getAllUserDetails() throws InvalidNameException, InvalidIdLengthException, InvalidEmailException, InvalidPasswordException {
		// TODO Auto-generated method stub
		return userrepository.getAllUserDetails();
	}

}

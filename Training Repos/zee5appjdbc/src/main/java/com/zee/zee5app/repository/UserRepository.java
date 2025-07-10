package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;

public interface UserRepository {
	public String addUser(Register register);
	public String updateUser(String id, Register register);
	public Optional<Register> getUserById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException, InvalidEmailException, InvalidPasswordException;
	public Register[] getAllUsers() throws InvalidNameException, InvalidIdLengthException, InvalidEmailException, InvalidPasswordException;
	public Optional<List<Register>> getAllUserDetails() throws InvalidIdLengthException, InvalidNameException, InvalidEmailException, InvalidPasswordException;
	public String deleteUserById(String id) throws IdNotFoundException;
}

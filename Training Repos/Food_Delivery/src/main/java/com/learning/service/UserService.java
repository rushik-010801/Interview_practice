package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;

import com.learning.dto.Register;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.RecordAlreadyExistsException;

public interface UserService {
	public Register addUser(Register register) throws RecordAlreadyExistsException, IdNotFoundException;
	public Register[] getAllUsers();
	public Register getUserById(int id) throws IdNotFoundException;
	public Register updateUserById(int id, Register register) throws IdNotFoundException, RecordAlreadyExistsException;
	public String deleteUserById(int id) throws IdNotFoundException;
	public String Authenticate(Credential credential); 
}

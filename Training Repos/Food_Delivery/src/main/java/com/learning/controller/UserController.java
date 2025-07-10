package com.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.Register;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.RecordAlreadyExistsException;
import com.learning.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	//this method is mapped to addUser in UserServiceImpl
	@PostMapping("/register")
	public ResponseEntity<?> addUser(@Valid @RequestBody Register register) throws RecordAlreadyExistsException, IdNotFoundException {
			Register res = userService.addUser(register);
			System.out.println(res);
			return ResponseEntity.status(201).body(res);
	}
	
	//this method is mapped to Authenticate in UserServiceImpl
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@Valid @RequestBody Credential credential) throws RecordAlreadyExistsException, IdNotFoundException {
			String res = userService.Authenticate(credential);
			Map<String, String> map = new HashMap<>();
			if(res.equals("success")) {
				map.put("Message" , "Success");
				return ResponseEntity.status(201).body(map);
			}
			else {
				map.put("Message" , "fail");
				return ResponseEntity.status(403).body(map);
			}
	}
	
	//this method is mapped to getAllUsers in UserServiceImpl
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsersDetails(){
		Register[] register = userService.getAllUsers();
		if(register.length == 0) {
			Map<String, String> map = new HashMap<>();
			map.put("Message" , "No record found.");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(register);
	}
	
	//this method is mapped to getById in UserServiceImpl
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id) throws IdNotFoundException{
		Register res = userService.getUserById(id);
		return ResponseEntity.status(200).body(res);
	}
	
	//this method is mapped to deleteUserById in UserServiceImpl
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) throws IdNotFoundException {
		String res = userService.deleteUserById(id);
		Map<String, String> map = new HashMap<>();
		if(res.equals("success")) {
			map.put("Message" , "User Deleted Successfully.");
			return ResponseEntity.status(201).body(map);
		}
		else {
			map.put("Message" , "User Not Deleted.");
			return ResponseEntity.status(404).body(map);
		}
	}
	
	//this method is mapped to updateUserById in UserServiceImpl
	@PutMapping("/users/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody Register register) throws IdNotFoundException, RecordAlreadyExistsException {
		Register res = userService.updateUserById(id, register);
		if(res != null)
			return ResponseEntity.status(201).body(res);
		else {
			Map<String, String> map = new HashMap<>();
			map.put("Message" , "User this ID not found.");
			return ResponseEntity.status(403).body(map);
		}
			
	}
}

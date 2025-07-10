package com.learning.controller.controlleradvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learning.exception.IdNotFoundException;
import com.learning.exception.RecordAlreadyExistsException;

@ControllerAdvice
public class ExceptionAdivce {
	//this class should be used when any userdefined exception is caused th
	//throughout all the controller
	
	@ExceptionHandler(RecordAlreadyExistsException.class)
	public ResponseEntity<?> recordalreadyRecordExistsException(){
		Map<String, String> map = new HashMap<>();
		map.put("Message" , "This record already exists");
		return ResponseEntity.status(404).body(map);
	}
	
	//Used for unknown exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandler(Exception e){
		Map<String, String> map = new HashMap<>();
		map.put("Message" , "Unknown Exception" + e.getMessage());
		return ResponseEntity.status(404).body(map);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> idNotFoundException(Exception e){
		Map<String, String> map = new HashMap<>();
		map.put("Message" , "Sorry user with "+e.getMessage()+" not found.");
		return ResponseEntity.status(404).body(map);
	}
}

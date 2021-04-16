package com.example.demo.exception;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NurseExceptionHandler {

	@ExceptionHandler(PropertyValueException.class)
	public ResponseEntity<String> handleIntegrityConstraint(PropertyValueException exception) {
		return new ResponseEntity<String>("Exception while processing REST Requests\n" + exception.getMessage(),
				HttpStatus.BAD_REQUEST);
	}

}

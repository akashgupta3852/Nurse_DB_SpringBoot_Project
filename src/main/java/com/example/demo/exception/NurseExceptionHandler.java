package com.example.demo.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NurseExceptionHandler {

	@ExceptionHandler(PropertyValueException.class)
	public ResponseEntity<String> handleIntegrityConstraint(PropertyValueException exception) {
		return new ResponseEntity<String>("Exception while processing REST Requests\n" + exception.getMessage(),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
		List<String> errMsg = errorList.stream().map(objErr -> objErr.getDefaultMessage()).collect(Collectors.toList());
		return new ResponseEntity<String>("Exception while processing REST Requests\n" + errMsg, HttpStatus.BAD_REQUEST);
	}

}

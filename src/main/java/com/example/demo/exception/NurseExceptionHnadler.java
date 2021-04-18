package com.example.demo.exception;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NurseExceptionHnadler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
		List<String> errMsg = errorList.stream().map(objErr -> objErr.getDefaultMessage()).collect(Collectors.toList());
		return new ResponseEntity<String>("Exception while processing REST Requests" + errMsg, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handle(HttpMessageNotReadableException exception) {
		String error = "Exception while processing REST Requests, startDate should be in the dd MMM yyyy format.";
		return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
	}
}

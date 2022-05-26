package com.simplilearn.webservice.exception.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.simplilearn.webservice.exception.AlreadyExist;
import com.simplilearn.webservice.exception.InvalidException;
import com.simplilearn.webservice.exception.NotFound;

@ControllerAdvice
public class GlobalExceptionHandler {

	ExceptionReponse response;
	
	@ExceptionHandler(NotFound.class)
	public ResponseEntity<ExceptionReponse> notFound(NotFound ex) {
		response = new ExceptionReponse(ex.getMessage(), HttpStatus.NOT_FOUND.name(), ex.getClass().getSimpleName());
		ResponseEntity<ExceptionReponse> exception = new ResponseEntity<ExceptionReponse>(response,HttpStatus.NOT_FOUND);
		return exception;
	}
	
	
	@ExceptionHandler(AlreadyExist.class)
	public ResponseEntity<ExceptionReponse> alreadyExist(AlreadyExist ex) {
		response = new ExceptionReponse(ex.getMessage(), HttpStatus.BAD_REQUEST.name(), ex.getClass().getSimpleName());
		ResponseEntity<ExceptionReponse> exception = new ResponseEntity<ExceptionReponse>(response,HttpStatus.BAD_REQUEST);
		return exception;
	}
	
	@ExceptionHandler(InvalidException.class)
	public ResponseEntity<ExceptionReponse> invalidDataException(InvalidException ex) {
		response = new ExceptionReponse(ex.getMessage(), HttpStatus.BAD_REQUEST.name(), ex.getClass().getSimpleName());
		ResponseEntity<ExceptionReponse> exception = new ResponseEntity<ExceptionReponse>(response,HttpStatus.BAD_REQUEST);
		return exception;
	}
}

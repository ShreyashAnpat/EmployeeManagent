package com.api.Exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.api.DAO.ResponceHandler;

@ControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(RunTimeException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleEntityNotFoundException(RunTimeException ex) {
		return ResponceHandler.responceHandler("", ex.status, ex.getRequesId(), ex.getMessage());
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus( HttpStatus.NOT_FOUND )
	public ResponseEntity<?> dataIntegrityViolationException(DataIntegrityViolationException ex) {
		return ResponceHandler.responceHandler("", HttpStatus.BAD_REQUEST, "", ex.getMessage());
	}
	

}

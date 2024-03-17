package com.api.Exception;

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
	        return ResponceHandler.ResponceHandler("", HttpStatus.NOT_FOUND, ex.getRequesId() , ex.getMessage() );
	  }
		
}

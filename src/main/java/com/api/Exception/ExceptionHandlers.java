package com.api.Exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.api.DAO.ResponceHandler;

@ControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(RunTimeException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleEntityNotFoundException(RunTimeException ex) {
		return ResponceHandler.responceHandler("", ex.status, ex.getRequesId(), ex.getMessage());
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus( HttpStatus.BAD_REQUEST )
	public ResponseEntity<?> dataIntegrityViolationException(DataIntegrityViolationException ex) {
		return ResponceHandler.responceHandler("", HttpStatus.BAD_REQUEST, "", ex.getMessage());
	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus( HttpStatus.BAD_REQUEST )
	public ResponseEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
		return ResponceHandler.responceHandler("", HttpStatus.BAD_REQUEST, "", "Invalid Request Body");
	}
	

	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus( HttpStatus.BAD_REQUEST )
	public ResponseEntity<?> noResourceFoundException(NoResourceFoundException ex) {
		return ResponceHandler.responceHandler("", HttpStatus.BAD_REQUEST , "", "Invalid Method ");
	}
	
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus( HttpStatus.BAD_REQUEST )
	public ResponseEntity<?> noResourceFoundException(MissingServletRequestParameterException ex) {
		return ResponceHandler.responceHandler("", HttpStatus.BAD_REQUEST , "", ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus( HttpStatus.BAD_REQUEST )
	public ResponseEntity<?> methodArgsNotValidException(MethodArgumentNotValidException ex) {		
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
		return ResponceHandler.responceHandler("", HttpStatus.BAD_REQUEST, "",errors.toString());
	}
	
	
	
	
}

package com.api.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.* ;

import com.api.DAO.ErrorDao;

import jakarta.persistence.EntityNotFoundException;
@ControllerAdvice
public class ExceptionHandler {
	
//	 @ResponseStatus(HttpStatus.NOT_FOUND)
//	  public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex) {
//		 	ErrorDao errorDto = new ErrorDao();
//		 	
//		 	errorDto.setData("");
//		 	errorDto.setStatus_code(HttpStatus.NOT_FOUND.toString());
//		 	errorDto.setStatus("Not Found");
//		 	errorDto.setStatus_msg(ex.getMessage());
//	        return ResponseEntity.notFound().build();
//	  }
		
}

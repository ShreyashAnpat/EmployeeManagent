package com.api.Exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RunTimeException extends RuntimeException {

	String RequesId ;
	HttpStatus status ;
	
	public RunTimeException(String message ,  HttpStatus status  ,String RequesId ) {
		super(message);
		this.RequesId  = RequesId ;	
		this.status = status ;
		
	}
}

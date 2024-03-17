package com.api.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RunTimeException extends RuntimeException {

	String RequesId ;
	public RunTimeException(String message , String RequesId ) {
		super(message);
		this.RequesId  = RequesId ;		
	}
}

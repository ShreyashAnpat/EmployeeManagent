package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@Autowired
	private Environment env ;

	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<>("Hello ...!", HttpStatus.OK);
	}

	@PostMapping("/hello")
	public ResponseEntity<String> helloName(@RequestBody String name) {
		if (name.isEmpty())
			return new ResponseEntity<>("Please Enter Name " + name, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>("Hello " + name, HttpStatus.OK);
	}
	
	
	@PostMapping("/myproperties")
	public ResponseEntity<String> myproperties(@RequestParam("propertyName") String propertyName){
		
		if(propertyName.isEmpty())return new ResponseEntity<>("Please Enter Name " + propertyName, HttpStatus.BAD_REQUEST);
		else {
			String propoerty = env.getProperty("spring.datasource.url");
			return new ResponseEntity<>( propoerty, HttpStatus.OK);
		}
	}
	
	
	


}

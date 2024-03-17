package com.api.DAO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponceHandler {
	
	public static ResponseEntity<?> ResponceHandler(Object responseObj,HttpStatus status_code ,String reqid ,String...message  ){
			
		String status = "error" ;
		LocalDateTime timestamp =  LocalDateTime.now(ZoneId.of("GMT+08:00"));
		String msg  = message.length > 0 ? message[0] : status_code.getReasonPhrase();
		
		if(status_code == HttpStatus.OK ) {
			status = "Success";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("status_code",status_code.value());
        map.put("status_msg", msg);
        map.put("data", responseObj);
        map.put("reqid", reqid);
        map.put("server_ts", timestamp.toString());
        return new ResponseEntity<Object>(map,status_code);
	}
}

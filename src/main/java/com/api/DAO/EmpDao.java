package com.api.DAO;

import lombok.Data;

@Data
public class EmpDao {

	private String empid;

	private String fname;

	private String dob;

	private String doj;

	private Integer salary;

	private String reportsto;

	private String client_reqid;
	
	private String  deptid; // Department

	private String rankid; // Ranks

}

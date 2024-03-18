package com.api.DAO;

import org.hibernate.annotations.UuidGenerator;

import lombok.Data;

@Data
public class EmpDeptRank {

	
	private String empid;

	private String fname;

	private String dob;

	private String doj;

	private Integer salary;

	private String reportsto;
	
	@UuidGenerator
	private String client_reqid;
	
	
	private DepartmentDao  dept; // Department

	private RankDao rank; // Ranks
	
}

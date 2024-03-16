package com.api.Entity;

import com.api.DAO.DepartmentDao;
import com.api.DAO.RankDao;

import lombok.Data;

@Data
public class EmpDeptRank {

	private String id;
	
	private String empid;

	private String fname;

	private String dob;

	private String doj;

	private Integer salary;

	private String reportsto;


	private String client_reqid;
	
	
	private DepartmentDao  dept; // Department

	private RankDao rank; // Ranks
	
}

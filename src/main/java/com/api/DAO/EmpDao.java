package com.api.DAO;

import com.api.Entity.Department;
import com.api.Entity.Ranks;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class EmpDao {

	private String id;
	
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

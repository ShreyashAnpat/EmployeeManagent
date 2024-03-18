package com.api.DAO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmpDao {

	
	@NotNull @NotBlank(message = "empid is mandatory")
	private String empid;

	@NotNull @NotBlank(message = "fname is mandatory")
	private String fname;

	@NotNull @NotBlank(message = "dob is mandatory")
	private String dob;

	@NotNull @NotBlank(message = "doj is mandatory")
	private String doj;

	@NotNull (message = "salary is mandatory")
	private Integer salary;

	@NotNull @NotBlank(message = "reportsto is mandatory")
	private String reportsto;

//	@NotNull(message = "client_reqid is mandatory")
	private String client_reqid;
	
	@NotNull @NotBlank(message = "deptid is mandatory")
	private String  deptid; // Department

	@NotNull @NotBlank(message = "rankid is mandatory")
	private String rankid; // Ranks

}

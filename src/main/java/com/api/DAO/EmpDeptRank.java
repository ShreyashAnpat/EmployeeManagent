package com.api.DAO;

import org.hibernate.annotations.UuidGenerator;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmpDeptRank {

	@NotNull @NotBlank(message = "empid is mandatory")
	private String empid;

	@NotNull @NotBlank(message = "fname is mandatory")
	private String fname;

	@NotNull @NotBlank(message = "dob is mandatory")
	private String dob;

	@NotNull @NotBlank(message = "doj is mandatory")
	private String doj;

	@NotNull(message = "salary is mandatory")
	private Integer salary;
	
	@NotNull @NotBlank(message = "reportsto is mandatory")
	private String reportsto;
	
	@UuidGenerator
	private String client_reqid;
	
	@NotNull(message = "reportsto is mandatory")
	@Valid
	private DepartmentDao  dept; // Department
	
	@NotNull (message = "reportsto is mandatory")
	@Valid
	private RankDao rank; // Ranks
	
}

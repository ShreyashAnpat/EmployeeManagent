package com.api.DAO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmpDtls {

	private String empid;

	private String fname;

	private String dob;

	private String doj;

	private Integer salary;

	private String reportManagerName;


	private String deptName; // Department

	private String rankDesc; // Ranks

}

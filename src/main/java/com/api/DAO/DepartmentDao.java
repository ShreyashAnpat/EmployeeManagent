package com.api.DAO;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepartmentDao {
	@Id
	@NotNull @NotBlank(message = "id is mandatory")
	private String id ;
	
	@NotNull @NotBlank(message = "deptname is mandatory")
	private String deptname ;
}

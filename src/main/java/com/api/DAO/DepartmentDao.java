package com.api.DAO;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class DepartmentDao {
	@Id
	private String id ;
	
	private String deptname ;
}

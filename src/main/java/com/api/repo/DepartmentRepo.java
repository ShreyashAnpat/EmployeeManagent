package com.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.Entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, String> {

}



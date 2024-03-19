package com.api.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.DAO.EmpNameIdDto;
import com.api.Entity.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee, String> {
	
	@Query(nativeQuery = true , value = "Select * from Employee where deptid_id =:deptId")
	public Optional<List<Employee>> getByDept(String deptId);
	
	@Query(nativeQuery = true , value = "Select * from Employee where empid =:empId")
	public Optional<Employee> findByempid(String empId);
	
	@Query(nativeQuery = true , value = "Select empid , fname from Employee")
	public List<EmpNameIdDto> getEmpList();

	@Query(nativeQuery = true , value = "Select empid , fname from Employee where fname like :filter")
	public List<EmpNameIdDto> getEmpList(String filter);

	@Query(nativeQuery = true , value = "Delete from Employee where empid =:empId")
	public List<EmpNameIdDto> deleteByEmpId(String empId);
}

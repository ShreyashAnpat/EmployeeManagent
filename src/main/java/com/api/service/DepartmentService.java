package com.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.DAO.DepartmentDao;
import com.api.Entity.Department;
import com.api.repo.DepartmentRepo;



@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepo repo;

	public ResponseEntity<?> addDepartment(DepartmentDao deptDao) {

		if (deptDao.getId().isEmpty() || deptDao.getDeptname().isEmpty()) {
			return new ResponseEntity<>( "Invalid id or Dept. Name" ,HttpStatus.BAD_REQUEST);
		} else {
			Department dept = new Department();
			dept.setId(deptDao.getId());
			dept.setDeptname(deptDao.getDeptname());
			repo.save(dept);
			return new ResponseEntity<Department>(dept, HttpStatus.OK);
		}

	}
	
	public ResponseEntity<?> getAllDept(){
		List<Department> deptList = repo.findAll();
		return new ResponseEntity<>(deptList , HttpStatus.OK);
	}
	
	public ResponseEntity<?> getDeptById(String id){
		if(id.isEmpty()) {
			return new ResponseEntity<>( "Enter Dept ID" +id,HttpStatus.BAD_REQUEST);
		}
		else {
			
			Optional<Department> dept = repo.findById(id);
			
			if(dept.isPresent()) {
				return new ResponseEntity<Department>(dept.get(), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>( "Department Not found for id " +id,HttpStatus.NOT_FOUND);
			}
			
		}
	}

}

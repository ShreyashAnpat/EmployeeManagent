package com.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.DAO.DepartmentDao;
import com.api.DAO.ResponceHandler;
import com.api.Entity.Department;
import com.api.Exception.RunTimeException;
import com.api.repo.DepartmentRepo;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepo repo;

	public ResponseEntity<?> addDepartment(DepartmentDao deptDao) {

		Boolean isDepartmentPresent = repo.findById(deptDao.getId()).isPresent();
		if (isDepartmentPresent)
			throw new RunTimeException ("Department Already Exist", HttpStatus.NOT_FOUND, "");

		Department dept = new Department();
		dept.setId(deptDao.getId());
		dept.setDeptname(deptDao.getDeptname());
		repo.save(dept);
		
		return ResponceHandler.responceHandler("Department Added Successfuly.", HttpStatus.OK, "");

	}

	public ResponseEntity<?> getAllDept() {
		List<Department> deptList = repo.findAll();

		if (deptList.size() <= 0)
			throw new RunTimeException("Department Not Found", HttpStatus.NOT_FOUND, "");

		return ResponceHandler.responceHandler(deptList, HttpStatus.OK, "");

	}

	public ResponseEntity<?> getDeptById(String id) {

		if (id.isEmpty())
			throw new RunTimeException("Enter Dept ID" + id, HttpStatus.BAD_REQUEST, "");

		Optional<Department> dept = repo.findById(id);

		if (dept.isPresent()) {
			return ResponceHandler.responceHandler(dept.get(), HttpStatus.OK, null);
		} else {
			throw new RunTimeException("Department Not found for id " + id, HttpStatus.NOT_FOUND, "");
		}

	}

}

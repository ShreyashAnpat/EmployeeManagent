package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import com.api.DAO.DepartmentDao;
import com.api.DAO.EmpDao;
import com.api.DAO.ErrorDao;
import com.api.DAO.RankDao;
import com.api.Entity.EmpDeptRank;
import com.api.Entity.Employee;
import com.api.service.DepartmentService;
import com.api.service.EmpService;
import com.api.service.RankService;

import jakarta.persistence.EntityNotFoundException;
@RestController
@RequestMapping("/myhr")
public class MyhrController {
	
	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	private EmpService empService ;
	
	@Autowired
	private RankService rankService ;
	
	@PostMapping(value = "/employee/add")
	public ResponseEntity<?> employeeadd(@RequestBody EmpDao emp){	
		return empService.addEmp(emp);
	}
	
	@GetMapping("/employee/getALL")
	public ResponseEntity<?> getAllEmp(){
		return empService.getALlEmp();
	}
	
	
	@PostMapping("/department/add")
	public ResponseEntity<?> addDepartment(@RequestBody DepartmentDao dept){
		return  deptService.addDepartment(dept) ;
	}
	
	@GetMapping("/department/getAll")
	public ResponseEntity<?> getAllDept(){
		return deptService.getAllDept();
	}
	
	@PostMapping("/rank/add")
	public ResponseEntity<?> addRank(@RequestBody RankDao rankdao){
		return  rankService.addRank(rankdao) ;
	}
	
	@GetMapping("/rank/getAll")
	public ResponseEntity<?> getAllRankDao(){
		return rankService.getAllRank();
	}

	@GetMapping("/employee/getAllEmp/{deptId}")
	public ResponseEntity<?> getAllEmpByDeptId(@PathVariable("deptId") String deptId){
		return empService.getAllEmpByDept(deptId);
	}
	
	
	@PostMapping("/employee/addEmpWithRankAndDept")
	public ResponseEntity<?> addEmpWithRankAndDept(@RequestBody EmpDeptRank EmpDeptRank ){
		return empService.addEmpWithRankAndDept(EmpDeptRank );
	}
	
	 @ExceptionHandler(HttpClientErrorException.class)
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	  public ResponseEntity<?> handleEntityNotFoundException(HttpClientErrorException ex) {
		 	ErrorDao errorDto = new ErrorDao();
		 	errorDto.setData("");
		 	errorDto.setStatus_code(HttpStatus.NOT_FOUND.toString());
		 	errorDto.setStatus("Not Found");
		 	errorDto.setStatus_msg(ex.getMessage());
	        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
	 
	 }
	
}

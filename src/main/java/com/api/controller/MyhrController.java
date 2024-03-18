package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.DAO.DepartmentDao;
import com.api.DAO.EmpDao;
import com.api.DAO.EmpDeptRank;
import com.api.DAO.RankDao;
import com.api.service.DepartmentService;
import com.api.service.EmpService;
import com.api.service.RankService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/myhr")
@Validated
public class MyhrController {
	
	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	private EmpService empService ;
	
	@Autowired
	private RankService rankService ;
	
	@PostMapping(value = "/employee/add")
	public ResponseEntity<?> employeeadd(@Valid @RequestBody EmpDao emp){	
		return empService.addEmp(emp);
	}
	
	@GetMapping("/employee/getALL")
	public ResponseEntity<?> getAllEmp(){
		return empService.getALlEmp();
	}
	
	@PostMapping("/department/add")
	public ResponseEntity<?> addDepartment(@Valid @RequestBody DepartmentDao dept){
		return  deptService.addDepartment(dept) ;
	}
	
	@GetMapping("/department/getAll")
	public ResponseEntity<?> getAllDept(){
		return deptService.getAllDept();
	}
	
	@PostMapping("/rank/add")
	public ResponseEntity<?> addRank(@Valid  @RequestBody RankDao rankdao){
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
	public ResponseEntity<?> addEmpWithRankAndDept(@Valid  @RequestBody EmpDeptRank EmpDeptRank ){
//		return new ResponseEntity<>(EmpDeptRank , HttpStatus.OK);
		return empService.addEmpWithRankAndDept(EmpDeptRank );
	}
	
	
}

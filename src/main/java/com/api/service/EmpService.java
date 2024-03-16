package com.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.DAO.DepartmentDao;
import com.api.DAO.EmpDao;
import com.api.DAO.RankDao;
import com.api.Entity.Department;
import com.api.Entity.EmpDeptRank;
import com.api.Entity.Employee;
import com.api.Entity.Ranks;
import com.api.repo.EmpRepo;

@Service
public class EmpService {

	@Autowired
	private EmpRepo empRepo;

	@Autowired
	private DepartmentService deptService;

	@Autowired
	private RankService rankService;

	public ResponseEntity<?> addEmpWithRankAndDept(EmpDeptRank empDeptRank) {

		DepartmentDao dep = empDeptRank.getDept();
		Department deptData;
		ResponseEntity<?> deptResponEntity  = deptService.addDepartment(dep);
		if (deptResponEntity.getStatusCode() == HttpStatus.OK) {
			deptData = (Department) deptResponEntity.getBody();
		} else {
			System.out.println(deptResponEntity.getBody());
			return deptResponEntity;
		}

		
		Ranks ranks;
		RankDao rankDao = empDeptRank.getRank();
		ResponseEntity<?> rankResponceEntity = 	rankService.addRank(rankDao);
		if (rankResponceEntity.getStatusCode() == HttpStatus.OK) {
			ranks = (Ranks) rankResponceEntity.getBody();
		} else {
			return rankResponceEntity;
		}

		
		Employee emp = new Employee();

		emp.setId(empDeptRank.getId());

		emp.setEmpid(empDeptRank.getEmpid());

		emp.setFname(empDeptRank.getFname());

		emp.setDob(empDeptRank.getDob());

		emp.setDoj(empDeptRank.getDoj());

		emp.setSalary(empDeptRank.getSalary());

		emp.setReportsto(empDeptRank.getReportsto());

		emp.setClient_reqid(empDeptRank.getClient_reqid());

		emp.setDeptid(deptData);

		emp.setRankid(ranks);

		empRepo.save(emp);

		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}


	public ResponseEntity<?> getALlEmp() {
		List<Employee> empList = empRepo.findAll();

		return new ResponseEntity<>(empList, HttpStatus.OK);
	}

	public ResponseEntity<?> addEmp(EmpDao empDao) {

		if (empDao.getEmpid().isEmpty()) {
			return new ResponseEntity<>("Emp Id Not found", HttpStatus.BAD_REQUEST);
		} else {

			Department deptData;

			ResponseEntity<?> deptResponEntity = deptService.getDeptById(empDao.getDeptid());
			if (deptResponEntity.getStatusCode() == HttpStatus.OK) {
				deptData = (Department) deptResponEntity.getBody();
			} else {
				System.out.println(deptResponEntity.getBody());
				return deptResponEntity;
			}

			Ranks ranks;
			ResponseEntity<?> rankResponceEntity = rankService.getRankById(empDao.getRankid());
			if (rankResponceEntity.getStatusCode() == HttpStatus.OK) {
				ranks = (Ranks) rankResponceEntity.getBody();
			} else {
				return rankResponceEntity;
			}

			Employee emp = new Employee();

			emp.setId(empDao.getId());

			emp.setEmpid(empDao.getEmpid());

			emp.setFname(empDao.getFname());

			emp.setDob(empDao.getDob());

			emp.setDoj(empDao.getDoj());

			emp.setSalary(empDao.getSalary());

			emp.setReportsto(empDao.getReportsto());

			emp.setClient_reqid(empDao.getClient_reqid());

			emp.setDeptid(deptData);

			emp.setRankid(ranks);

			empRepo.save(emp);

			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		}

	}

	public ResponseEntity<?> getAllEmpByDept(String dept) {

		Optional<List<Employee>> empList = empRepo.getByDept(dept);

		return new ResponseEntity<>(empList, HttpStatus.OK);

	}
}

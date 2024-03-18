package com.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.DAO.DepartmentDao;
import com.api.DAO.EmpDao;
import com.api.DAO.EmpDeptRank;
import com.api.DAO.RankDao;
import com.api.DAO.ResponceHandler;
import com.api.Entity.Department;
import com.api.Entity.Employee;
import com.api.Entity.Ranks;
import com.api.Exception.RunTimeException;
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

		Boolean empExist = empRepo.findByempid(empDeptRank.getEmpid()).isPresent();

		if (!empExist) {

			DepartmentDao dep = empDeptRank.getDept();
			Department deptData;
			ResponseEntity<?> deptResponEntity = deptService.addDepartment(dep);
			if (deptResponEntity.getStatusCode() == HttpStatus.OK) {
				@SuppressWarnings("unchecked")
				Map<String, Object>  resp = (Map<String, Object>) deptResponEntity.getBody();
				deptData = (Department) resp.get("data");
			} else {
				System.out.println(deptResponEntity.getBody());
				return deptResponEntity;
			}

			Ranks ranks;
			RankDao rankDao = empDeptRank.getRank();
			ResponseEntity<?> rankResponceEntity = rankService.addRank(rankDao);
			if (rankResponceEntity.getStatusCode() == HttpStatus.OK) {
				ranks = (Ranks) rankResponceEntity.getBody();
			} else {
				return rankResponceEntity;
			}

			Employee emp = new Employee();

			emp.setEmpid(empDeptRank.getEmpid());

			emp.setFname(empDeptRank.getFname());

			emp.setDob(empDeptRank.getDob());

			emp.setDoj(empDeptRank.getDoj());

			emp.setSalary(empDeptRank.getSalary());

			emp.setReportsto(empDeptRank.getReportsto());

			emp.setDeptid(deptData);

			emp.setRankid(ranks);

			empRepo.save(emp);

			return ResponceHandler.responceHandler("Employee Added successfully.", HttpStatus.OK,
					empDeptRank.getClient_reqid());

		} else {
			throw new RunTimeException("Employee alredy exist", HttpStatus.BAD_REQUEST, "");
		}

	}

	public ResponseEntity<?> addEmp(EmpDao empDao) {

		Boolean empExist = empRepo.findByempid(empDao.getEmpid()).isPresent();

		if (!empExist) {

			Department deptData;

			ResponseEntity<?> deptResponEntity = deptService.getDeptById(empDao.getDeptid());
			if (deptResponEntity.getStatusCode() == HttpStatus.OK) {
				@SuppressWarnings("unchecked")
				Map<String, Object>  resp = (Map<String, Object>) deptResponEntity.getBody();
				deptData = (Department) resp.get("data");
			} else {
				
				return deptResponEntity;
			}

			Ranks ranks;
			ResponseEntity<?> rankResponceEntity = rankService.getRankById(empDao.getRankid());
			if (rankResponceEntity.getStatusCode() == HttpStatus.OK) {
				@SuppressWarnings("unchecked")
				Map<String , Object> resp =  (Map<String, Object>)rankResponceEntity.getBody();
				ranks = (Ranks) resp.get("data"); ;
			} else {
				return rankResponceEntity;
			}

			Employee emp = new Employee();

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

			return ResponceHandler.responceHandler(emp, HttpStatus.OK, "");
		} else {
			throw new RunTimeException("Employee alredy exist", HttpStatus.BAD_REQUEST, "");
		}
	}

	public ResponseEntity<?> getAllEmpByDept(String dept) {

		List<Employee> empList = empRepo.getByDept(dept).get();
		if (empList.size() > 0) {
			return ResponceHandler.responceHandler(empList, HttpStatus.OK, "");
		} else
			throw new RunTimeException("Employee Not Found in " + dept, HttpStatus.NOT_FOUND, "");

	}

	public ResponseEntity<?> getALlEmp() {
		List<Employee> empList = empRepo.findAll();
		if (empList.size() > 0) {
			return ResponceHandler.responceHandler(empList, HttpStatus.OK, "");
		} else
			throw new RunTimeException("Employee Not Found", HttpStatus.NOT_FOUND, "");
	}
}

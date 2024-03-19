package com.api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.DAO.DepartmentDao;
import com.api.DAO.EmpDao;
import com.api.DAO.EmpDeptRank;
import com.api.DAO.EmpDtls;
import com.api.DAO.EmpNameIdDto;
import com.api.DAO.RankDao;
import com.api.DAO.ResponceHandler;
import com.api.Entity.Department;
import com.api.Entity.Employee;
import com.api.Entity.Ranks;
import com.api.Exception.RunTimeException;
import com.api.repo.EmpRepo;

import jakarta.validation.Valid;

@Service
public class EmpService {

	@Autowired
	private EmpRepo empRepo;

	@Autowired
	private DepartmentService deptService;

	@Autowired
	private RankService rankService;
	
	public ResponseEntity<?> deleteEmp(String empId){	
//		Boolean res
//		if()
		empRepo.deleteByEmpId(empId);	
		return ResponceHandler.responceHandler("Emploay Deleted Successfully", HttpStatus.OK, "");
	}

	public ResponseEntity<?> addEmpWithRankAndDept(EmpDeptRank empDeptRank) {

		Boolean empExist = empRepo.findByempid(empDeptRank.getEmpid()).isPresent();

		if (!empExist) {

			DepartmentDao dep = empDeptRank.getDept();
			Department deptData = new Department();
			ResponseEntity<?> deptResponEntity = deptService.addDepartment(dep);
			if (deptResponEntity.getStatusCode() == HttpStatus.OK) {
				deptData.setId(dep.getId());
				deptData.setDeptname(dep.getDeptname());
			} else {
				return deptResponEntity;
			}

			Ranks ranks = new Ranks();
			RankDao rankDao = empDeptRank.getRank();
			ResponseEntity<?> rankResponceEntity = rankService.addRank(rankDao);
			if (rankResponceEntity.getStatusCode() == HttpStatus.OK) {
				ranks.setId(rankDao.getId());
				ranks.setParentrankid(rankDao.getParentrankid());
				ranks.setRankdesc(ranks.getParentrankid());
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
				Map<String, Object> resp = (Map<String, Object>) deptResponEntity.getBody();
				deptData = (Department) resp.get("data");
			} else {

				return deptResponEntity;
			}

			Ranks ranks;
			ResponseEntity<?> rankResponceEntity = rankService.getRankById(empDao.getRankid());
			if (rankResponceEntity.getStatusCode() == HttpStatus.OK) {
				@SuppressWarnings("unchecked")
				Map<String, Object> resp = (Map<String, Object>) rankResponceEntity.getBody();
				ranks = (Ranks) resp.get("data");
				;
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

	public ResponseEntity<?> getEmpList(String filter) {

		List<EmpNameIdDto> empList;

		if (filter == null)
			empList = empRepo.getEmpList();
		else
			empList = empRepo.getEmpList("%" + filter + "%");

		if (empList.size() <= 0)
			throw new RunTimeException("Employee not found", HttpStatus.NOT_FOUND, "");

		return ResponceHandler.responceHandler(empList, HttpStatus.OK, "");
	}

	public ResponseEntity<?> getEmpDtlsById(String empid) {

		Optional<Employee> empResp = empRepo.findByempid(empid);

		if (empResp.isEmpty())
			throw new RunTimeException("Employee not found", HttpStatus.NOT_FOUND, "");

		Employee emp = empResp.get();

		Optional<Employee> reportingManager = empRepo.findByempid(emp.getReportsto());

		EmpDtls empDtls = new EmpDtls();

		empDtls.setEmpid(emp.getEmpid());
		empDtls.setDob(emp.getDob());
		empDtls.setDoj(emp.getDoj());
		empDtls.setFname(emp.getFname());
		empDtls.setSalary(emp.getSalary());
		empDtls.setDeptName(emp.getDeptid().getDeptname());
		empDtls.setRankDesc(emp.getRankid().getRankdesc());

		if (reportingManager.isPresent())
			empDtls.setReportManagerName(reportingManager.get().getFname());
		else
			empDtls.setReportManagerName("");

		return ResponceHandler.responceHandler(empDtls, HttpStatus.OK, "");
	}

	public ResponseEntity<?> updateEmp(@Valid EmpDao empDao) {

		Optional<Employee> empExist = empRepo.findByempid(empDao.getEmpid());

		if (!empExist.isPresent())
			throw new RunTimeException("Employee Not exist with Id " + empDao.getEmpid(), HttpStatus.BAD_REQUEST, "");

		Department deptData;

		ResponseEntity<?> deptResponEntity = deptService.getDeptById(empDao.getDeptid());
		if (deptResponEntity.getStatusCode() == HttpStatus.OK) {
			@SuppressWarnings("unchecked")
			Map<String, Object> resp = (Map<String, Object>) deptResponEntity.getBody();
			deptData = (Department) resp.get("data");
		} else {

			return deptResponEntity;
		}

		Ranks ranks;
		ResponseEntity<?> rankResponceEntity = rankService.getRankById(empDao.getRankid());
		if (rankResponceEntity.getStatusCode() == HttpStatus.OK) {
			@SuppressWarnings("unchecked")
			Map<String, Object> resp = (Map<String, Object>) rankResponceEntity.getBody();
			ranks = (Ranks) resp.get("data");
			;
		} else {
			return rankResponceEntity;
		}

		Employee emp = empExist.get();
		
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

	}
}

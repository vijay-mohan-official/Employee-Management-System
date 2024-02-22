package com.retailcloud.employee.service;

import com.retailcloud.employee.dto.EmployeeDTO;
import com.retailcloud.employee.dto.EmployeeIdNamePageDTO;
import com.retailcloud.employee.dto.EmployeePageDTO;
import com.retailcloud.employee.dto.EmployeeRequest;
import com.retailcloud.employee.dto.ReportChainDTO;

public interface EmployeeService {
	void createEmployee(EmployeeRequest request);
	void updateEmployee(Long id, EmployeeRequest request);
	void moveDeptEmp(Long empId, Long deptId);
//	List<EmployeeDTO> getAllEmployees();
	EmployeePageDTO getAllEmployees(Integer page, Integer size);
//	List<EmployeeIdNameDTO> findEmpNameIDs();
	EmployeeIdNamePageDTO findEmpNameIDs(Integer page, Integer size);
	EmployeeDTO getEmployeeById(Long id);
	ReportChainDTO getEmployeeChain(Long empId);
}

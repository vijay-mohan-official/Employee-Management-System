package com.retailcloud.employee.service;

import com.retailcloud.employee.dto.DepartmentDTO;
import com.retailcloud.employee.dto.DepartmentEmployeeDTO;
import com.retailcloud.employee.dto.DepartmentPageDTO;
import com.retailcloud.employee.dto.DepartmentRequest;

public interface DepartmentService {
	void addDept(DepartmentRequest request);
	void deleteDept(Long id);
	void updateDept(Long id, DepartmentRequest request);
//	List<DepartmentDTO> getDepts();
	DepartmentPageDTO getDepts(Integer page, Integer size);
	DepartmentDTO getDepartmentById(Long id);
	DepartmentEmployeeDTO getDeptWithEmployees(Long id);
}

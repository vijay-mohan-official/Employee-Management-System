package com.retailcloud.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retailcloud.employee.dto.DepartmentDTO;
import com.retailcloud.employee.dto.DepartmentEmployeeDTO;
import com.retailcloud.employee.dto.DepartmentPageDTO;
import com.retailcloud.employee.dto.DepartmentRequest;
import com.retailcloud.employee.response.CustomResponse;
import com.retailcloud.employee.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService deptService;

	@PostMapping("/add")
	public ResponseEntity<CustomResponse> createDepartment(@RequestBody DepartmentRequest request) {
		deptService.addDept(request);
		CustomResponse response = new CustomResponse("Created Department");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/delete/{departmentId}")
	public ResponseEntity<CustomResponse> deleteDepartment(@PathVariable() Long departmentId) {
		deptService.deleteDept(departmentId);
		CustomResponse response = new CustomResponse("Deleted Department");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/update/{departmentId}")
	public ResponseEntity<?> updateDepartment(@PathVariable Long departmentId, @RequestBody DepartmentRequest request) {
		deptService.updateDept(departmentId, request);
		CustomResponse response = new CustomResponse("Updated Department");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping
	public ResponseEntity<DepartmentPageDTO> getAllDepartments(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size) {
		DepartmentPageDTO departments = deptService.getDepts(page,size);
		return ResponseEntity.ok(departments);
	}

	@GetMapping("/{departmentId}")
	public ResponseEntity<?> getDepartment(@PathVariable Long departmentId, @RequestParam(required = false) String expand) {
		if ("employee".equals(expand)) {
			DepartmentEmployeeDTO deptEmployees = deptService.getDeptWithEmployees(departmentId);
			return ResponseEntity.ok(deptEmployees);
		} else {
			DepartmentDTO department = deptService.getDepartmentById(departmentId);
			return ResponseEntity.ok(department);
		}
	}
}

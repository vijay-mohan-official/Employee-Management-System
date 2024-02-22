package com.retailcloud.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retailcloud.employee.dto.EmployeeDTO;
import com.retailcloud.employee.dto.EmployeeIdNamePageDTO;
import com.retailcloud.employee.dto.EmployeePageDTO;
import com.retailcloud.employee.dto.EmployeeRequest;
import com.retailcloud.employee.response.CustomResponse;
import com.retailcloud.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@PostMapping("/create")
	public ResponseEntity<CustomResponse> createEmployee(@RequestBody EmployeeRequest request){
		empService.createEmployee(request);
		CustomResponse response = new CustomResponse("Created Employee");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CustomResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest request){
		empService.updateEmployee(id, request);
		CustomResponse response = new CustomResponse("Updated Employee");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/{empId}/move/{deptId}")
	public ResponseEntity<CustomResponse> moveEmployeeDept(@PathVariable Long empId, @PathVariable Long deptId) {
		empService.moveDeptEmp(empId, deptId);
		CustomResponse response = new CustomResponse("Moved Employee to new Department");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping
	public ResponseEntity<EmployeePageDTO> getAllEmployees(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size) {
		EmployeePageDTO employees = empService.getAllEmployees(page,size);
		return ResponseEntity.ok(employees);
	}

	@GetMapping("/empnameid")
	public ResponseEntity<?> listEmpNameAndId(@RequestParam(name = "lookup", required = false) boolean lookup, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size){
		if(lookup) {
			EmployeeIdNamePageDTO empList = empService.findEmpNameIDs(page,size);
			return ResponseEntity.ok(empList);
		}
		else {
			return ResponseEntity.badRequest().body("{\"error\": \"bad request\"}");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id){
		EmployeeDTO employeeDTO = empService.getEmployeeById(id);
		return ResponseEntity.ok(employeeDTO);
	}

	@GetMapping("/reportchain/{empId}")
	public ResponseEntity<?> getReportingChain(@PathVariable Long empId){
//		ReportChainDTO chain = empService.getEmployeeChain(empId);
		return ResponseEntity.ok("yet to implement");
	}

}

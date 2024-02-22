package com.retailcloud.employee.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class DepartmentEmployeeDTO {	
	private Long deptId;
    private String deptName;
    private Date createDate;
    private EmployeeDTO deptHead;
    private List<EmployeeDeptDTO> employees; 
}

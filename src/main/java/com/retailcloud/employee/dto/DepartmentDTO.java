package com.retailcloud.employee.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DepartmentDTO {
	private Long deptId;
    private String deptName;
    private Date createDate;
    private EmployeeDeptDTO deptHead;
}

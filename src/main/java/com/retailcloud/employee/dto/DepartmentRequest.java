package com.retailcloud.employee.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DepartmentRequest {
	private String name;
	private Date creationDate;
	private Long deptHeadId;
}

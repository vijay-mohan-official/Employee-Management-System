package com.retailcloud.employee.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeDTO {
	private Long empId;
	private String empName;
    private Date dob;
    private double salary;
    private String address;
    private String title;
    private Date joiningDate;
    private float bonusPercentage;
    private Long departmentId;
    private Long managerId;
    private String managerName;
    private String deptName;
}

package com.retailcloud.employee.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeRequest {
    private String name;
    private Date dateOfBirth;
    private double salary;
    private String address;
    private String role;
    private Date joiningDate;
    private float yearlyBonusPercentage;
    private Long departmentId;
    private Long managerId;
}

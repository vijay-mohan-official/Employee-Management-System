package com.retailcloud.employee.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long empId;
	
	private String empName;
	private Date dob;
	private Double salary;
	private String address;
	private String title;
	private Date joiningDate;
	private float bonusPercentage;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", referencedColumnName = "deptId")
	private Department department;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id", referencedColumnName = "empId")
	private Employee reportingManager;
}

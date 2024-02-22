package com.retailcloud.employee.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Department {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long deptId;
	
	private String deptName;
	private Date createDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_head_id", referencedColumnName = "empId")
	private Employee deptHead;
	
	@OneToMany(mappedBy = "department")
    private List<Employee> employees;
}

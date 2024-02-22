package com.retailcloud.employee.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeIdNamePageDTO {
	private List<EmployeeIdNameDTO> employees;
	private int pages;
	private int size;
}

package com.retailcloud.employee.dto;

import java.util.List;

import lombok.Data;

@Data
public class ReportChainDTO {
	private EmployeeDTO reportingManager;
	private List<EmployeeDTO> reportingChain;
}

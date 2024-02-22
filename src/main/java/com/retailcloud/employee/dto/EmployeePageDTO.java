package com.retailcloud.employee.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeePageDTO {
    private List<EmployeeDTO> employees;
    private int currentPage;
    private int totalPages;
}

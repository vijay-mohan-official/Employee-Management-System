package com.retailcloud.employee.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentPageDTO {
    private List<DepartmentDTO> depts;
    private int currentPage;
    private int totalPages;
}

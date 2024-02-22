package com.retailcloud.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.retailcloud.employee.dto.DepartmentDTO;
import com.retailcloud.employee.dto.DepartmentEmployeeDTO;
import com.retailcloud.employee.dto.DepartmentPageDTO;
import com.retailcloud.employee.dto.DepartmentRequest;
import com.retailcloud.employee.entity.Department;
import com.retailcloud.employee.entity.Employee;
import com.retailcloud.employee.repository.DepartmentRepository;
import com.retailcloud.employee.repository.EmployeeRepository;
import com.retailcloud.employee.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository repo;

	@Autowired
	private EmployeeRepository empRepo;

	private ModelMapper mapper = new ModelMapper();

	@Override
	public void addDept(DepartmentRequest request) {
		// TODO Auto-generated method stub
		Department dept = new Department();
		dept.setCreateDate(request.getCreationDate());
		dept.setDeptName(request.getName());
		if(request.getDeptHeadId() != null) {
			Optional<Employee> emp = empRepo.findById(request.getDeptHeadId());
			if(!emp.isPresent()) {
				throw new RuntimeException("Employee "+request.getDeptHeadId()+" not found");
			}
			dept.setDeptHead(emp.get());
		}
		try {
			repo.save(dept);
		}catch(DataIntegrityViolationException ex) {
			throw new RuntimeException("Employee "+request.getDeptHeadId()+" is already head of another department");
		}
	}

	@Override
	public void deleteDept(Long id) {
		// TODO Auto-generated method stub
		Optional<Department> dept = repo.findById(id);
		if(!dept.isPresent()) {
			throw new RuntimeException("Department not found");
		}
		if(!dept.get().getEmployees().isEmpty()) {
			throw new RuntimeException("Cannot delete Department which has employees");
		}
		repo.delete(dept.get());;
	}

	@Override
	public void updateDept(Long id, DepartmentRequest request){
		// TODO Auto-generated method stub
		Optional<Department> deptExist = repo.findById(id);
		if(!deptExist.isPresent()) {
			throw new RuntimeException("Department "+ id +" Not Found");
		}
		deptExist.get().setDeptName(request.getName());
		deptExist.get().setCreateDate(request.getCreationDate());
		if(request.getDeptHeadId() != null) {
			Optional<Employee> deptHead = empRepo.findById(request.getDeptHeadId());
			if(!deptHead.isPresent()) {
				throw new RuntimeException("Employee not found with ID "+request.getDeptHeadId());
			}
			deptExist.get().setDeptHead(deptHead.get());
		}
		try {
			repo.save(deptExist.get());
		}catch(DataIntegrityViolationException ex) {
			throw new RuntimeException("Employee "+request.getDeptHeadId()+" is already head of another department");
		}
	}

	@Override
	public DepartmentPageDTO getDepts(Integer page, Integer size) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(page, size);
		Page<Department> depts = repo.findAll(pageable);
		if(depts != null && !depts.isEmpty()) {
			List<DepartmentDTO> deptDTO = new ArrayList<>();
			for(Department dept : depts) {
				DepartmentDTO tempDTO = mapper.map(dept, DepartmentDTO.class);
				deptDTO.add(tempDTO);
			}
			return new DepartmentPageDTO(deptDTO,page,depts.getTotalPages());
		}
		else {
			List<DepartmentDTO> emptyList = new ArrayList<>();
			return new DepartmentPageDTO(emptyList,page,0);
		}
	}

	@Override
	public DepartmentDTO getDepartmentById(Long id) {
		// TODO Auto-generated method stub
		Optional<Department> dept = repo.findById(id);
		if(!dept.isPresent()) {
			throw new RuntimeException("Department not found");
		}
		return mapper.map(dept, DepartmentDTO.class);
	}

	@Override
	public DepartmentEmployeeDTO getDeptWithEmployees(Long id) {
		// TODO Auto-generated method stub
		Optional<Department> dept = repo.findById(id);
		if(!dept.isPresent()) {
			throw new RuntimeException("Department not found");
		}
		DepartmentEmployeeDTO deptEmps = mapper.map(dept, DepartmentEmployeeDTO.class); 
		return deptEmps;
	}
}

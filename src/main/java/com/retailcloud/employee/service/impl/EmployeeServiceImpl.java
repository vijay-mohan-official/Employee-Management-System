package com.retailcloud.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.retailcloud.employee.dto.EmployeeDTO;
import com.retailcloud.employee.dto.EmployeeIdNameDTO;
import com.retailcloud.employee.dto.EmployeeIdNamePageDTO;
import com.retailcloud.employee.dto.EmployeePageDTO;
import com.retailcloud.employee.dto.EmployeeRequest;
import com.retailcloud.employee.dto.ReportChainDTO;
import com.retailcloud.employee.entity.Department;
import com.retailcloud.employee.entity.Employee;
import com.retailcloud.employee.repository.DepartmentRepository;
import com.retailcloud.employee.repository.EmployeeRepository;
import com.retailcloud.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repo;

	@Autowired
	private DepartmentRepository deptRepo;

	private ModelMapper mapper = new ModelMapper();

	@Override
	public void createEmployee(EmployeeRequest request) {
		// TODO Auto-generated method stub
		Employee employee = new Employee();
		employee.setEmpName(request.getName());
		employee.setDob(request.getDateOfBirth());
		employee.setSalary(request.getSalary());
		employee.setAddress(request.getAddress());
		employee.setTitle(request.getRole());
		employee.setJoiningDate(request.getJoiningDate());
		employee.setBonusPercentage(request.getYearlyBonusPercentage());

		Optional<Department> department = deptRepo.findById(request.getDepartmentId());
		if(!department.isPresent()) {
			throw new RuntimeException("Department "+ request.getDepartmentId() +" not found");
		}
		employee.setDepartment(department.get());

		if (request.getManagerId() != null) {
			Optional<Employee> manager = repo.findById(request.getManagerId());
			if(!manager.isPresent()) {
				throw new RuntimeException("Manager "+ request.getManagerId() +" not found");
			}
			employee.setReportingManager(manager.get());
		}
		repo.save(employee);
	}

	@Override
	public void updateEmployee(Long id, EmployeeRequest request) {
		// TODO Auto-generated method stub
		Optional<Employee> empExist = repo.findById(id);
		if(!empExist.isPresent()) {
			throw new RuntimeException("Employee "+id+" Not Found");
		}

		Employee exist = empExist.get();
		exist.setAddress(request.getAddress());
		exist.setBonusPercentage(request.getYearlyBonusPercentage());

		Optional<Department> department = deptRepo.findById(request.getDepartmentId());
		if(!department.isPresent()) {
			throw new RuntimeException("Department "+ request.getDepartmentId() +" not found");
		}
		exist.setDepartment(department.get());

		exist.setDob(request.getDateOfBirth());
		exist.setEmpId(id);
		exist.setEmpName(request.getName());
		exist.setJoiningDate(request.getJoiningDate());

		if (request.getManagerId() != null) {
			Optional<Employee> manager = repo.findById(request.getManagerId());
			if(!manager.isPresent()) {
				throw new RuntimeException("Manager "+ request.getManagerId() +" not found");
			}
			exist.setReportingManager(manager.get());
		}

		exist.setSalary(request.getSalary());
		exist.setTitle(request.getRole());

		repo.save(exist);
	}

	@Override
	public void moveDeptEmp(Long empId, Long deptId){
		// TODO Auto-generated method stub
		Optional<Employee> empExist = repo.findById(empId);
		if(!empExist.isPresent()) {
			throw new RuntimeException("Employee "+ empId +" Not Found");
		}
		Optional<Department> newDept = deptRepo.findById(deptId);
		if(!newDept.isPresent()){
			throw new RuntimeException("Department "+ deptId +" Not Found");			
		}
		empExist.get().setDepartment(newDept.get());
		repo.save(empExist.get());
	}

	@Override
	public EmployeeIdNamePageDTO findEmpNameIDs(Integer page,Integer size) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(page, size);
		Page<Employee> employees = repo.findAll(pageable);
		if(employees != null && !employees.isEmpty()) {
			List<EmployeeIdNameDTO> empDTO = new ArrayList<>();
			for(Employee emp : employees) {
				EmployeeIdNameDTO tempDTO = new EmployeeIdNameDTO();
				tempDTO.setId(emp.getEmpId());
				tempDTO.setName(emp.getEmpName());
				empDTO.add(tempDTO);
			}
			return new EmployeeIdNamePageDTO(empDTO,page,employees.getTotalPages());
		}
		else {
			List<EmployeeIdNameDTO> emptyList = new ArrayList<>();
			return new EmployeeIdNamePageDTO(emptyList,page,0);
		}
	}

	@Override
	public EmployeePageDTO getAllEmployees(Integer page, Integer size) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(page, size);
		Page<Employee> employees = repo.findAll(pageable);
		if(employees != null && !employees.isEmpty()) {
			List<EmployeeDTO> empDto = new ArrayList<>();
			for(Employee emp : employees) {
				EmployeeDTO tempDTO = mapper.map(emp,EmployeeDTO.class);
				tempDTO.setDeptName(emp.getDepartment().getDeptName());
				tempDTO.setDepartmentId(emp.getDepartment().getDeptId());
				if(emp.getReportingManager() != null) {
					tempDTO.setManagerId(emp.getReportingManager().getEmpId());
					tempDTO.setManagerName(emp.getReportingManager().getEmpName());
				}
				empDto.add(tempDTO);
			}
			return new EmployeePageDTO(empDto,page,employees.getTotalPages());
		}
		else {
			List<EmployeeDTO> emptyList = new ArrayList<>();
			return new EmployeePageDTO(emptyList,page,0);
		}
	}

	@Override
	public EmployeeDTO getEmployeeById(Long id){
		// TODO Auto-generated method stub
		Optional<Employee> emp = repo.findById(id);
		if(!emp.isPresent()) {
			throw new RuntimeException("Employee "+ id +" Not Found");
		}
		EmployeeDTO empDTO = mapper.map(emp,EmployeeDTO.class);
		if(emp.get().getReportingManager() != null) {
			empDTO.setManagerName(emp.get().getReportingManager().getEmpName());
			empDTO.setManagerId(emp.get().getReportingManager().getEmpId());
		}
		if(emp.get().getDepartment() != null) {
			empDTO.setDeptName(emp.get().getDepartment().getDeptName());
			empDTO.setDepartmentId(emp.get().getDepartment().getDeptId());
		}
		return empDTO;
	}

	@Override
	public ReportChainDTO getEmployeeChain(Long empId) {
		// TODO Auto-generated method stub
		Optional<Employee> employee = repo.findById(empId);
        if(!employee.isPresent()) {
        	throw new RuntimeException("Employee "+empId+" not found.");
        }

        ReportChainDTO reportChainDTO = new ReportChainDTO();
        reportChainDTO.setReportingManager(mapper.map(employee,EmployeeDTO.class));
        reportChainDTO.setReportingChain(getReportingChain(employee.get().getReportingManager()));

        return reportChainDTO;		
	}

	private List<EmployeeDTO> getReportingChain(Employee reportingManager) {
		// TODO Auto-generated method stub
		if (reportingManager == null || reportingManager.getReportingManager() == null) {
            return new ArrayList<>(); 
        }
        List<EmployeeDTO> reportingChain = new ArrayList<>();
        addReportingChain(reportingManager.getReportingManager(),reportingChain);
        return reportingChain;
	}

	private void addReportingChain(Employee reportingManager, List<EmployeeDTO> reportingChain) {
		// TODO Auto-generated method stub
		if (reportingManager.getReportingManager() != null) {
			addReportingChain(reportingManager.getReportingManager(), reportingChain);
        }
        reportingChain.add(mapper.map(reportingManager,EmployeeDTO.class));
		
	}

}

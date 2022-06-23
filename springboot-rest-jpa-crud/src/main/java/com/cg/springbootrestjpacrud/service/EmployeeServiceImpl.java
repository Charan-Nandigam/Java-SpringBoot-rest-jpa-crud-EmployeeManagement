package com.cg.springbootrestjpacrud.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.springbootrestjpacrud.entities.Employee;
import com.cg.springbootrestjpacrud.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;
	
	@Override
	public Employee createEmployee(Employee emp) {
		return empRepository.save(emp);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return empRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Long empId) {
		return empRepository.findById(empId);
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		return empRepository.save(emp);
	}

	@Override
	public void deleteEmployee(Employee emp) {
		empRepository.delete(emp);
	}

	@Override
	public List<Employee> getEmployeesByLastName(String lastName) {
		return empRepository.findByLastName(lastName);
	}

	@Override
	public Optional<Employee> getEmployeeByEmailId(String emailId) {
		return empRepository.findByEmailId(emailId);
	}

}

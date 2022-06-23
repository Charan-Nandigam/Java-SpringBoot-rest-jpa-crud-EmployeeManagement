package com.cg.springbootrestjpacrud.service;

import java.util.List;
import java.util.Optional;

import com.cg.springbootrestjpacrud.entities.Employee;

public interface EmployeeService {
	public Employee createEmployee(Employee emp);
	public List<Employee> getAllEmployees();
	public Optional<Employee> getEmployeeById(Long empId);
	public Employee updateEmployee(Employee emp);
	public void deleteEmployee(Employee emp);
	public List<Employee> getEmployeesByLastName(String lastName);
	public Optional<Employee> getEmployeeByEmailId(String emailId);
}

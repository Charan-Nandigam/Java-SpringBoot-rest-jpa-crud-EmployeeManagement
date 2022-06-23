package com.cg.springbootrestjpacrud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.springbootrestjpacrud.entities.Employee;
import com.cg.springbootrestjpacrud.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class) 
public class TestEmployeeService {
	@InjectMocks
	EmployeeServiceImpl empService;
	@Mock
	EmployeeRepository empRepository;
	
	@Test
	public void testCreateEmployee() {
		Employee emp = new Employee(1, "william", "smith", "ws@gmail.com");
		empService.createEmployee(emp);
		verify(empRepository, times(1)).save(emp);
	}
	
	@Test
	public void testGetAllEmployees() {
		List<Employee> empList = new ArrayList<Employee>();
		Employee emp1 = new Employee(1, "william", "smith", "ws@gmail.com");
		Employee emp2 = new Employee(2, "sam", "curran", "sc@gmail.com");
		Employee emp3 = new Employee(3, "tom", "curran", "tc@gmail.com");
		empList.add(emp1);
		empList.add(emp2);
		empList.add(emp3);
		when(empRepository.findAll()).thenReturn(empList);
		List<Employee> eList = empService.getAllEmployees();
		assertEquals(3, eList.size());
		verify(empRepository, times(1)).findAll();
		
	}
	
	@Test
	public void testGetEmployeeById() {
		when(empRepository.findById(1L)).thenReturn(Optional.of(new Employee(1, "william", "smith", "ws@gmail.com")));
		Optional<Employee> emp = empService.getEmployeeById(1L);
		assertEquals(true, emp.isPresent());
	}
	
	@Test
	public void testUpdateEmployee() {
		Employee emp = new Employee(1, "william", "smith", "ws@gmail.com");
		emp.setFirstName("John");
		empService.updateEmployee(emp);
		assertEquals("John", emp.getFirstName());
		
	}
	
	@Test
	public void testDeleteEmployee() {
		Employee emp = new Employee(1, "william", "smith", "ws@gmail.com");
		empService.deleteEmployee(emp);
		Optional<Employee> employee = empService.getEmployeeById(1L);
		assertEquals(false, employee.isPresent());
	}
	


}

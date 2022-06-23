package com.cg.springbootrestjpacrud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.springbootrestjpacrud.entities.Employee;
import com.cg.springbootrestjpacrud.exceptions.EmployeeNotFoundException;
import com.cg.springbootrestjpacrud.service.EmployeeService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/employees/newemp")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return empService.createEmployee(emp);
	}
	
	@GetMapping("/employees/all")
	public List<Employee> getAllEmployees(){
		return empService.getAllEmployees();
	}
	
	@GetMapping("/employees/id/{id}")
	public ResponseEntity<Employee> getEmployeeById( @PathVariable(value = "id") Long empId) throws EmployeeNotFoundException {
		Employee emp = empService.getEmployeeById(empId).orElseThrow(()-> new EmployeeNotFoundException("Employee not found with this Id : "+ empId));
		return ResponseEntity.ok().body(emp);			
	}
	
	@PutMapping("/employees/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long empId, @Valid @RequestBody Employee empDets) throws EmployeeNotFoundException {
		Employee emp = empService.getEmployeeById(empId).orElseThrow(()-> new EmployeeNotFoundException("Employee not found with this Id : "+ empId));
		empDets.setId(emp.getId());
		Employee updatedEmp = empService.updateEmployee(empDets);
		return ResponseEntity.ok(updatedEmp);			
	}
	
	@DeleteMapping("/employees/delete/{id}")
	public Map<String, Boolean> deleteEmployee( @PathVariable(value = "id") Long empId) throws EmployeeNotFoundException {
		Employee emp = empService.getEmployeeById(empId).orElseThrow(()-> new EmployeeNotFoundException("Employee not found with this Id : "+ empId));
		empService.deleteEmployee(emp);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;			
	}
	
	@GetMapping("/employees/lastname/{lastname}")
	public List<Employee> getEmployeesByLastName(@PathVariable(value = "lastname") String lastName){
		return empService.getEmployeesByLastName(lastName);
	}
	
	@GetMapping("/employees/emailid/{emailid}")
	public ResponseEntity<Employee> getEmployeeByEmailId( @PathVariable(value = "emailid") String emailId) throws EmployeeNotFoundException {
		Employee emp = empService.getEmployeeByEmailId(emailId).orElseThrow(()-> new EmployeeNotFoundException("Employee not found with this EmailId : "+ emailId));
		return ResponseEntity.ok().body(emp);			
	}
}

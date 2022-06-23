package com.cg.springbootrestjpacrud.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.cg.springbootrestjpacrud.SpringbootRestJpaCrudApplication;
import com.cg.springbootrestjpacrud.entities.Employee;

@SpringBootTest(classes = SpringbootRestJpaCrudApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:"+port+"/api/employees";
	}
	
	@Test
	public void testCreateEmployee() {
		Employee emp = new Employee();
		emp.setFirstName("karthik");
		emp.setLastName("kumar");
		emp.setEmailId("kk@abc.com");
		ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl()+"/newemp", emp, Employee.class);
		assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testGetAllEmployees() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl()+"/all", HttpMethod.GET, entity, String.class);
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testGetEmployeeById() {
		Employee emp = restTemplate.getForObject(getRootUrl()+"/id/41", Employee.class);
		System.out.println("emp Name = "+emp.getFirstName());
		assertNotNull(emp);
	}
	
	@Test
	public void testUpdateEmployee() {
		int id = 41;
		Employee emp = restTemplate.getForObject(getRootUrl()+"/id/"+id, Employee.class);
		emp.setLastName("Reddy");
		emp.setEmailId("kr@abc.com");
		restTemplate.put(getRootUrl()+"/update/"+id, emp);
		Employee updemp = restTemplate.getForObject(getRootUrl()+"/id/"+id, Employee.class);
		assertNotNull(updemp);
	}
	
	@Test
	public void testDeleteEmployee() {
		int id = 34;
		Employee emp = restTemplate.getForObject(getRootUrl()+"/id/"+id, Employee.class);
		restTemplate.delete(getRootUrl()+"/delete/"+id);
		Employee e = restTemplate.getForObject(getRootUrl()+"/id/"+id, Employee.class);
		assertEquals(0, e.getId());
	}

}

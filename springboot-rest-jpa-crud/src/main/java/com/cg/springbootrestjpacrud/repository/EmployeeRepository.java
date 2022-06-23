package com.cg.springbootrestjpacrud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.springbootrestjpacrud.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

	public List<Employee> findByLastName(String lastName);
	public Optional<Employee> findByEmailId(String emailId);
}

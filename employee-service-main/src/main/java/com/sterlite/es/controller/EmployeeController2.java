package com.sterlite.es.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sterlite.es.entity.Employee;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:3000/"})
@RestController
@RequestMapping("/employee/demo")
@RequiredArgsConstructor
public class EmployeeController2 {
	
	@Autowired
	private final AuthenticationService service;
	
	@GetMapping("/email/{email}")
	public Employee getEmployeeById(@PathVariable String email) {
		return service.getById(email);
	}
	
	
	
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Employee> getAllEmployees(){
		return service.getAllEmployees();
	}

}

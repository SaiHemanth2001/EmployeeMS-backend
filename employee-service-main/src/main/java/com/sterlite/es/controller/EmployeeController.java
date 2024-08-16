package com.sterlite.es.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sterlite.es.entity.Employee;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:3000/"})
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
	
	@Autowired
	private final AuthenticationService service;
	
	@PostMapping("/saveEmployee")
	public ResponseEntity<AuthenticationResponse> registerEmployee(@RequestBody RegisterRequest request) throws Exception {
		return ResponseEntity.ok(service.saveEmployee(request));
	}
	
	@PutMapping("/updateEmployee/{email}")
	public ResponseEntity<AuthenticationResponse> updateEmployee(@RequestBody RegisterRequest request,@PathVariable String email) throws Exception {
		return ResponseEntity.ok(service.updateEmployee(request,email));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(service.authenticate(request));
	}
	
	@GetMapping("/manager/{manager}")
	public List<Employee> getEmployees (@PathVariable String manager){
		return service.getEmployeesUnderManager(manager);
	}
	
	
	
	

}

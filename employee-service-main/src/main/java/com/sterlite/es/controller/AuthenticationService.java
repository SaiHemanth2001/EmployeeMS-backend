package com.sterlite.es.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sterlite.es.config.JwtService;
import com.sterlite.es.entity.Employee;
import com.sterlite.es.entity.Role;
import com.sterlite.es.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final EmployeeRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse saveEmployee(RegisterRequest request) throws Exception {

		if (repository.managerExists(request.getManager()) == 1&&request.getEmail().substring(request.getEmail().indexOf('@'), request.getEmail().length()).equals("@gmail.com")&&repository.employeeCheck(request.getEmail())==0) {
			var employee = Employee.builder().firstName(request.getFirstName()).lastName(request.getLastName())
					.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
					.location(request.getLocation()).manager(request.getManager()).role(Role.USER).build();
			repository.save(employee);
			var jwtToken = jwtService.generateToken(employee);
			return AuthenticationResponse.builder().token(jwtToken).build();
		}
		else {
			throw new Exception("Manager not found");
		}
	}

	public String authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var employee = repository.findByEmail(request.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("employee not found"));
		var jwtToken = jwtService.generateToken(employee);

		return jwtToken;

	}

	public Employee getById(String email){
		return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("employee not found"));
	}

	public List<Employee> getEmployeesUnderManager(String manager) {
		
		return repository.findByManagerName(manager);
	}

	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public AuthenticationResponse updateEmployee(RegisterRequest request, String email) throws Exception {
		if (request.getEmail().substring(request.getEmail().indexOf('@'), request.getEmail().length()).equals("@gmail.com")) {
			var employee= repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("employee not found"));
			employee.setFirstName(request.getFirstName());
			employee.setLastName(request.getLastName());
			employee.setEmail(request.getEmail());
			employee.setPassword(passwordEncoder.encode(request.getPassword()));
			employee.setLocation(request.getLocation());
			
			repository.save(employee);
			var jwtToken = jwtService.generateToken(employee);
			return AuthenticationResponse.builder().token(jwtToken).build();
			
		}
		else {
			throw new Exception("Manager not found (or) email error");
		}
	}

}

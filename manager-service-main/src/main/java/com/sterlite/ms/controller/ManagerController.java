package com.sterlite.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000/"})
@RestController
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private AuthenticationService service;
	
	@PostMapping("/saveManager")
	public ResponseEntity<AuthenticationResponse> registerManager(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(service.saveManager(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(service.authenticate(request));
	}
	
}

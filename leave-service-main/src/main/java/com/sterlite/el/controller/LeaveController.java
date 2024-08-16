package com.sterlite.el.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sterlite.el.entity.EmployeeLeave;
import com.sterlite.el.service.LeaveService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:3000/"})
@RestController
@RequestMapping("/leave")
@RequiredArgsConstructor
public class LeaveController {
	
	@Autowired
	private LeaveService service;
	
	@PostMapping("/apply")
	public EmployeeLeave applyLeave(@RequestBody EmployeeLeave leave) throws Exception {
		return service.apply(leave);
	}
	
	@PutMapping("/approve/{email}")
	public EmployeeLeave approve(@RequestBody EmployeeLeave leave,@PathVariable String email) throws Exception {
		return service.approveLeave(leave,email);
	}
	
	@PutMapping("/reject/{email}")
	public EmployeeLeave rejectLeave(@RequestBody EmployeeLeave leave,@PathVariable String email) throws Exception {
		return service.rejectLeave(leave,email);
	}
	
	
	@GetMapping("/email/{email}")
	public List<EmployeeLeave> getByEmail(@PathVariable String email) {
		return service.getByEmail(email);
	}
	
	@GetMapping("/manager/{manager}")
	public List<EmployeeLeave> getByManager(@PathVariable String manager) {
		return service.getByManager(manager);
	}
	

}

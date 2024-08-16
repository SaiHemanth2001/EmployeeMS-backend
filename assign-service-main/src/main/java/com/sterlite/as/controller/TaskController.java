package com.sterlite.as.controller;

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

import com.sterlite.as.entity.Assign_Task;
import com.sterlite.as.service.TaskService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:3000/"})
@RestController
@RequestMapping("/assign")
@RequiredArgsConstructor
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@PostMapping("/task")
	public Assign_Task assignTask(@RequestBody Assign_Task task) throws Exception {
		return service.addTask(task);
	}
	
	@PutMapping("/inprogress/{email}")
	public Assign_Task inProgress(@RequestBody Assign_Task leave,@PathVariable String email) throws Exception {
		return service.inProgress(leave,email);
	}
	
	@PutMapping("/done/{email}")
	public Assign_Task taskDone(@RequestBody Assign_Task leave,@PathVariable String email) throws Exception {
		return service.taskDone(leave,email);
	}
	
	@GetMapping("/email/{email}")
	public List<Assign_Task> getByEmail(@PathVariable String email) {
		return service.getByEmail(email);
	}
	
	@GetMapping("/manager/{manager}")
	public List<Assign_Task> getByManager(@PathVariable String manager) {
		return service.getByManager(manager);
	}
	
	
	
	

}

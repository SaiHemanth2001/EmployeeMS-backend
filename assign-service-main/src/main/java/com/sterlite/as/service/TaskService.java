package com.sterlite.as.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterlite.as.entity.Assign_Task;
import com.sterlite.as.entity.CheckStatus;
import com.sterlite.as.repository.TaskRepository;


@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repository;

	public Assign_Task addTask(Assign_Task task) throws Exception {
		
		if(repository.employeeExists(task.getEmail())==1) {
			return repository.save(task);
			}
			else {
				throw new Exception();
			}
	}
	
	

	public Assign_Task inProgress(Assign_Task task, String email) throws Exception {
		
		if(repository.employeeExists(task.getEmail())==1&&repository.managerCheck(task.getEmail(),task.getManager())==1) {
			Assign_Task existingtask =repository.findByEmail(email);
			existingtask.setCheckStatus(CheckStatus.IN_PROGRESS);
			repository.save(existingtask);
			return existingtask;
			}
			else {
				throw new Exception();
			}
	}

	public Assign_Task taskDone(Assign_Task task, String email) throws Exception {
		
		if(repository.employeeExists(task.getEmail())==1&&repository.managerCheck(task.getEmail(),task.getManager())==1) {
			Assign_Task existingtask =repository.findByEmail(email);
			existingtask.setCheckStatus(CheckStatus.DONE);
			repository.save(existingtask);
			return existingtask;
			}
			else {
				throw new Exception();
			}
		
	}



	public List<Assign_Task> getByManager(String manager) {
		return repository.findByManager(manager);
	}



	public List<Assign_Task> getByEmail(String email) {
		return repository.findByEmail1(email);
	}
	
	
	
	

}

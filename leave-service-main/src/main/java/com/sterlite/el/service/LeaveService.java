package com.sterlite.el.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterlite.el.entity.Approval;
import com.sterlite.el.entity.EmployeeLeave;
import com.sterlite.el.repository.LeaveRepository;

@Service
public class LeaveService {
	
	@Autowired
	private LeaveRepository repository;

	public EmployeeLeave apply(EmployeeLeave leave) throws Exception {
		if(repository.employeeExists(leave.getEmail())==1) {
		return repository.save(leave);
		}
		else {
			throw new Exception();
		}
	}

	public List<EmployeeLeave> getByEmail(String email) {
		return repository.findByEmail1(email);
	}

	public EmployeeLeave approveLeave(EmployeeLeave leave, String email) throws Exception {
		if(repository.employeeExists(leave.getEmail())==1&&repository.managerCheck(leave.getEmail(),leave.getManager())==1) {
			EmployeeLeave existingleave =repository.findByEmail(email);
			
			existingleave.setApproval(Approval.APPROVED);
			repository.save(existingleave);
			return existingleave;
			}
			else {
				throw new Exception();
			}
	}

	public List<EmployeeLeave> getByManager(String manager) {
		// TODO Auto-generated method stub
		return repository.findByManager(manager);
	}

	public EmployeeLeave rejectLeave(EmployeeLeave leave, String email) throws Exception {
		
		if(repository.employeeExists(leave.getEmail())==1&&repository.managerCheck(leave.getEmail(),leave.getManager())==1) {
			EmployeeLeave existingleave =repository.findByEmail(email);
			existingleave.setApproval(Approval.NOTAPPROVED);
			repository.save(existingleave);
			return existingleave;
			}
			else {
				throw new Exception();
			}
	}


	
	
	

}

package com.sterlite.el;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sterlite.el.entity.Approval;
import com.sterlite.el.entity.EmployeeLeave;
import com.sterlite.el.repository.LeaveRepository;


@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class LeaveServiceApplicationTests {

		@Autowired
		LeaveRepository repo ;
	
	@Test
	@Order(1)
	public void testCreateLeave() {
		
		EmployeeLeave leave = new EmployeeLeave();
		leave.setEmail("mike@gmail.com");
		leave.setDate("2023-03-30");
		leave.setManager("piyush");
		leave.setReason("Sick");
		leave.setApproval(Approval.NOTAPPROVED);
		
		repo.save(leave);
				
	
	}
	
	@Test
	@Order(2)
	public void testGetAllLeaves() {
		List<EmployeeLeave> list = repo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	@Test
	@Order(3)
	public void testSingleLeave() {
		EmployeeLeave m =repo.findByEmail("mike@gmail.com");
		assertEquals("piyush",m.getManager());
	}
	
	@Test
	@Order(4)
	public void testDelete() {
		EmployeeLeave m =repo.findByEmail("mike@gmail.com");
		repo.deleteById(m.getId());
	}

}

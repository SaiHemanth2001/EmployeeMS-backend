package com.sterlite.as;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sterlite.as.entity.Assign_Task;
import com.sterlite.as.entity.CheckStatus;
import com.sterlite.as.repository.TaskRepository;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class AssignServiceApplicationTests {

	@Autowired
	TaskRepository repo;
	
	@Test
	@Order(1)
	public void testCreateTask() {
		
		Assign_Task task = new Assign_Task();
		
		task.setEmail("mike@gmail.com");
		task.setDate("2023-03-29");
		task.setTodo("FullStack");
		task.setManager("piyush");
		task.setCheckStatus(CheckStatus.IN_PROGRESS);
		
		repo.save(task);
		
		assertNotNull(repo.findByEmail("mike@gmail.com"));
		
	}
	
	@Test
	@Order(2)
	public void testgettasks() {
		List<Assign_Task> list = repo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void getSingleTask() {
		Assign_Task m =repo.findByEmail("mike@gmail.com");
		assertEquals("piyush",m.getManager());
	}
	
	@Test
	@Order(4)
	public void testDelete() {
		Assign_Task m =repo.findByEmail("mike@gmail.com");
		repo.deleteById(m.getId());
	}
}

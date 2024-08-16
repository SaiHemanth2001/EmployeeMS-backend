package com.sterlite.ms;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sterlite.ms.entity.Manager;
import com.sterlite.ms.entity.Role;
import com.sterlite.ms.repository.ManagerRepository;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class ManagerServiceApplicationTests {

	@Autowired
	ManagerRepository repo;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	
	@Test
	@Order(1)
	public void testCreateManager() {
		
		Manager m = new Manager();
		m.setName("ankit");
		m.setEmail("ankit@gmail.com");
		m.setPassword(passwordEncoder.encode("ankit"));
		m.setRole(Role.MANAGER);
		
		repo.save(m);
		
		assertNotNull(repo.findByEmail("ankit@gmail.com").get());
		
	}
	
	@Test
	@Order(2)
	public void testgetAllManagers() {
		List<Manager> list =repo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void testSingleManager() {
		Manager e =repo.findByEmail("ankit@gmail.com").get();
		assertEquals("ankit",e.getName());
	}
	
	@Test
	@Order(4)
	public void testdelete() {
		Manager m =repo.findByEmail("ankit@gmail.com").get();
		repo.deleteById(m.getManager_id());
	}
	
	

}

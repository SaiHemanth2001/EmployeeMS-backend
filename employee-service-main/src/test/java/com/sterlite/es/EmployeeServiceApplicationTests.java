package com.sterlite.es;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.sterlite.es.entity.Employee;
import com.sterlite.es.entity.Role;
import com.sterlite.es.repository.EmployeeRepository;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class EmployeeServiceApplicationTests {

	@Autowired
	EmployeeRepository repository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	@Order(1)
	public void testcreateEmployee() {
		Employee e = new Employee();
		
		e.setFirstName("mike");
		e.setLastName("ross");
		e.setEmail("mike@gmail.com");
		e.setPassword((passwordEncoder.encode("mike")));
		e.setLocation("banglore");
		e.setManager("piyush");
		e.setRole(Role.USER);
		
		repository.save(e);
		
		assertNotNull(repository.findByEmail("mike@gmail.com").get());
		
	}
	
	@Test
	@Order(2)
	public void testReadAllEmployees() {
		List<Employee> list =repository.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void testSingleEmployee() {
		Employee m =repository.findByEmail("mike@gmail.com").get();
		assertEquals("piyush",m.getManager());
	}
	
	@Test
	@Order(4)
	public void testUpdate() {
		Employee e = repository.findByEmail("mike@gmail.com").get();
		e.setManager("piyush");
		repository.save(e);
		assertNotEquals("bala", repository.findByEmail("mike@gmail.com").get().getManager());
	}
	
	
	@Test
	@Order(5)
	public void testdelete() {
		Employee e = repository.findByEmail("mike@gmail.com").get();
		repository.deleteById(e.getId());
		
		
	}
	
	
	
	
	
	
	
}

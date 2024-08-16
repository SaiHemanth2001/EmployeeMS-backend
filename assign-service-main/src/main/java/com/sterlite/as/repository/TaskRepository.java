package com.sterlite.as.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sterlite.as.entity.Assign_Task;

public interface TaskRepository extends JpaRepository<Assign_Task, Long>{
	
	@Query(value=" SELECT EXISTS(SELECT * FROM employees e where e.email = ?1)", nativeQuery = true)
	long employeeExists(String email);

	Assign_Task findByEmail(String email);
	
	@Query(value=" SELECT * FROM employee_task e where e.email = ?1", nativeQuery = true)
	List<Assign_Task> findByEmail1(String email);

	List<Assign_Task> findByManager(String manager);
	
	@Query(value="SELECT EXISTS(SELECT * FROM employees e where e.email = ?1 AND e.manager=?2)", nativeQuery = true)
	long managerCheck(String email,String manager);

}

package com.sterlite.el.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sterlite.el.entity.EmployeeLeave;

public interface LeaveRepository extends JpaRepository<EmployeeLeave, Long>{
	
	@Query(value=" SELECT EXISTS(SELECT * FROM employees e where e.email = ?1)", nativeQuery = true)
	long employeeExists(String email);
	
	@Query(value=" SELECT * FROM employee_leave e where e.email = ?1", nativeQuery = true)
	List<EmployeeLeave> findByEmail1(String email);
	
	EmployeeLeave findByEmail(String email);

	List<EmployeeLeave> findByManager(String manager);
	@Query(value="SELECT EXISTS(SELECT * FROM employees e where e.email = ?1 AND e.manager=?2)", nativeQuery = true)
	long managerCheck(String email,String manager);

}

package com.sterlite.es.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.IntPredicate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sterlite.es.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	Optional<Employee> findByEmail(String email);
	
	@Query(value=" SELECT EXISTS(SELECT * FROM managers e where e.name = ?1)", nativeQuery = true)
	long managerExists(String manager);
	@Query(value=" SELECT EXISTS(SELECT * FROM employees e where e.email = ?1)", nativeQuery = true)
	long employeeCheck(String email);
	
	@Query(value=" SELECT * FROM employees e where e.manager = ?1", nativeQuery = true)
	List<Employee> findByManagerName(String manager);

	void deleteByEmail(String string);

}

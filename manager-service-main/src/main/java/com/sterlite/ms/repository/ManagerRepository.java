package com.sterlite.ms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sterlite.ms.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

	Optional<Manager> findByEmail(String email);
	
}

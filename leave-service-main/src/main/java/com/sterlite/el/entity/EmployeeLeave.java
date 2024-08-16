package com.sterlite.el.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee_leave")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLeave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="email")
	private String email;
	@Column(name="date")
	private String date;
	@Column(name="reason")
	private String reason;
	@Column(name="manager")
	private String manager;
	@Enumerated(EnumType.STRING)
	private Approval approval;

}

package com.sterlite.as.entity;

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
@Table(name="employee_task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assign_Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="email")
	private String email;
	@Column(name="date")
	private String date;
	@Column(name="manager")
	private String manager;
	@Column(name="todo")
	private String todo;
	@Enumerated(EnumType.STRING)
	private CheckStatus checkStatus;

}

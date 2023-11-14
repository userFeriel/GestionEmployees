package com.kafka.Spring.mission.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kafka.Spring.employe.entity.Employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="Mission")
public class Mission {
	@Id
	@GeneratedValue
	private Integer missionId;
	@NotBlank
	private String name;
	private String description;
	private String contactName;
	private String contactPhone;
	private String contactEmail;
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate startingDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate endingDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate estimatedEndingDate;
	@Column(columnDefinition = "boolean default true")
	private boolean actif;
	@ManyToOne
	@JoinColumn(name="employeeId")
	private Employee employee;
	
	

}

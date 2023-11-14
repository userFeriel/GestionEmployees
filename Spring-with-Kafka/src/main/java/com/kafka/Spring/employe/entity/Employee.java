package com.kafka.Spring.employe.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.kafka.Spring.mission.entity.Mission;
import com.kafka.Spring.technology.entity.Technology;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "Employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	@GeneratedValue
	private Integer employeeId;
	private String firstname;
	private String lastname;
	@Column(unique = true)
	private String employeeNumber;
	private String email;
	private LocalDate birthDate;
	private String password;
	@Column(columnDefinition = "boolean default false")
	private boolean inMission;
	@Column(columnDefinition = "boolean default true")
	private boolean actif;
	private int experience;
	private TypeEmployee typeEmployee;
	@ManyToMany(fetch = FetchType.LAZY, 
	cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(	name = "employee_technology",
	joinColumns = @JoinColumn(name = "employeeId"),
	inverseJoinColumns = @JoinColumn(name= "technologyId"))
	private List<Technology> technologies;
	@OneToMany(mappedBy = "employee")
	List<Mission> missions;
	@UpdateTimestamp
	private Instant lastUpdatedOn;
	
	
	public Integer getAge() {
		if(birthDate == null) return null;
		return Period.between(birthDate, LocalDate.now()).getYears();
	}

}

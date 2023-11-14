package com.kafka.Spring.technology.entity;

import java.util.List;

import com.kafka.Spring.employe.entity.Employee;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Technology")
public class Technology {
	@Id
	@GeneratedValue
	private Integer technologyId;
	@NotBlank
	private String name;
	private String description;
	private TypeTechnology type;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "technologies")
	private List<Employee> employees;
}
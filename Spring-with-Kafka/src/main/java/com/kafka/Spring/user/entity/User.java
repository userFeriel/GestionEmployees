package com.kafka.Spring.user.entity;

import java.util.List;



import com.kafka.Spring.role.entity.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Userr")
public class User {
	@Id
	@GeneratedValue
	private Integer userId;
	@Column(unique = true)
	private String username;
	private String firstname;
	private String lastname;
	@Column(unique = true)
	private String email;
	private String password;
	private int age;
	@Column(columnDefinition = "boolean default true")
	private boolean actif;
	@Column(columnDefinition = "boolean default true")
	private boolean canAccess;
	@ManyToMany(fetch = FetchType.LAZY, 
				cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(	name = "user_role",
				joinColumns = @JoinColumn(name = "userId"),
				inverseJoinColumns = @JoinColumn(name= "roleId"))
	private List<Role> roles;

}

package com.kafka.Spring.role.entity;

import java.util.List;



import com.kafka.Spring.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
@Id
@GeneratedValue
private Integer roleId;
@Column(unique = true)
private String roleName;
private String roleDescription;
// ce boolean nous indique si on peut affecter ce role ou bien seul l'admin peut le faire
@Column(columnDefinition = "boolean default true")
private boolean canBeAdded;
@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "roles")
private List<User> users;
}

package com.kafka.Spring.role.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafka.Spring.role.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
List<Role> findAllByRoleNameInAndCanBeAddedTrue(List<String> roleName);
}

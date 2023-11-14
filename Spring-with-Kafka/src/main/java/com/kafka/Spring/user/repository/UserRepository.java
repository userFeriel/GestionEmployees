package com.kafka.Spring.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafka.Spring.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
Optional<User> findByUsername(String username);
}

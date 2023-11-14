package com.kafka.Spring.employe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafka.Spring.employe.entity.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    Optional<Employee> findByEmployeeNumberAndActifTrue(String empNumber);


    List<Employee> findByActifTrue();
}

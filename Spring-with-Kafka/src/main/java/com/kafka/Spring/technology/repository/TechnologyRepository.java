package com.kafka.Spring.technology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafka.Spring.technology.entity.Technology;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Integer> {

}

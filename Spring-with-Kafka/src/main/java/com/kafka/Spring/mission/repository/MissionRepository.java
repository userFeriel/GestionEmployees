package com.kafka.Spring.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafka.Spring.mission.entity.Mission;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Integer>{
    List<Mission> findAllByActifTrue();
    Optional<Mission> findAllByMissionIdAndActifTrue(Integer missionId);
}

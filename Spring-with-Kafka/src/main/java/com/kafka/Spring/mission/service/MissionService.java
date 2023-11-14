package com.kafka.Spring.mission.service;

import com.kafka.Spring.mission.entity.Mission;
import com.kafka.Spring.mission.repository.MissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MissionService {
    private final MissionRepository missionRepository;

    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public boolean addMission(Mission mission) {
        try {
            missionRepository.save(mission);
            log.info("mission saved successfully {}", mission);
            return true;
        } catch (Exception e) {
            log.error("Exception in MissionService --> addMission {}", e.getMessage());
        }
        return false;

    }

    public Mission getMission(Integer missionId) {
        try {

            Optional<Mission> optionalMission = missionRepository.findAllByMissionIdAndActifTrue(missionId);
            if (optionalMission.isPresent()) {
                return optionalMission.get();
            }
        } catch (Exception e) {
            log.error("Exception in MissionService --> addMission {}", e.getMessage());
        }
        return null;

    }

    public boolean deleteMission(Integer idMission) {
        try {
            Optional<Mission> optionalMission = missionRepository.findById(idMission);
            if (optionalMission.isPresent()) {
                optionalMission.get().setActif(false);
                missionRepository.save(optionalMission.get());
                log.info("mission deleted successfully");
                return true;
            }
        } catch (Exception e) {
            log.error("Exception in MissionService --> deleteMission {}", e.getMessage());
        }
        return false;

    }

    public List<Mission> findAllMissions() {
        try {
            return missionRepository.findAllByActifTrue();

        } catch (Exception e) {
            log.error("Exception in MissionService --> findAllMissions {}", e.getMessage());
        }
        return Collections.emptyList();
    }
}
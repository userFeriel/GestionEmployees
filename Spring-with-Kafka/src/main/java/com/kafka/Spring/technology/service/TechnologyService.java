package com.kafka.Spring.technology.service;

import com.kafka.Spring.technology.entity.Technology;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.kafka.Spring.technology.repository.TechnologyRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TechnologyService {

    private TechnologyRepository technologyRepository;

    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public boolean addTechnologies(List<Technology> technologies) {
        try {
            technologyRepository.saveAll(technologies);
            log.info("technology added successfully");
            return true;
        } catch (Exception e) {
            log.error("Exception in TechnologyService --> addTechnologies {}", e.getMessage());
        }

        return false;
    }

    public boolean updateTechnology(Technology technology) {
        try {
            if (technology.getTechnologyId() != null) {
                Optional<Technology> optionalTechnology = technologyRepository.findById(technology.getTechnologyId());
                if (optionalTechnology.isPresent()) {
                    technologyRepository.save(optionalTechnology.get());
                    log.info("technology updated successfully {}", technology);
                    return true;
                }
            }

        } catch (Exception e) {
            log.error("Exception in TechnologyService --> updateTechnology {}", e.getMessage());
        }

        return false;
    }

    public boolean deleteTechnology(Integer technologyId) {
        try {
            Optional<Technology> optionalTechnology = technologyRepository.findById(technologyId);
            if (optionalTechnology.isPresent()) {
                technologyRepository.delete(optionalTechnology.get());
                log.info("technology deleted successfully {}", optionalTechnology.get());
                return true;
            }

        } catch (Exception e) {
            log.error("Exception in TechnologyService --> deleteTechnology {}", e.getMessage());
        }
        return false;
    }

    public List<Technology> findAllTechnology() {
        try {
            return technologyRepository.findAll();

        } catch (Exception e) {
            log.error("Exception in TechnologyService --> findAllTechnology {}", e.getMessage());
        }
        return Collections.emptyList();
    }
}

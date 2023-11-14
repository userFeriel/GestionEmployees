package com.kafka.Spring.employe.service;

import com.kafka.Spring.employe.dto.EmployeeDto;
import com.kafka.Spring.employe.entity.Employee;
import com.kafka.Spring.employe.mapper.EmployeeMapper;
import com.kafka.Spring.employe.repository.EmployeeRepository;
import com.kafka.Spring.employe.utils.EmployeeFilter;
import com.kafka.Spring.mission.entity.Mission;
import com.kafka.Spring.mission.repository.MissionRepository;
import com.kafka.Spring.technology.entity.Technology;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final MissionRepository missionRepository;

    @Autowired
    EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, MissionRepository missionRepository) {
        this.employeeRepository = employeeRepository;
        this.missionRepository = missionRepository;
    }

    /***
     * findAll actif employees
     * @return List<EmployeeDto>
     */
    public List<EmployeeDto> findAllEmployees() {
        try {
            return employeeMapper.employeeToEmployeeDto(employeeRepository.findByActifTrue());
        } catch (Exception e) {
            log.error("Exception in EmployeeService --> findAllEmployees {}", e.getMessage());
        }
        return new ArrayList<>();
    }

    /***
     *  add or update an employee
     * @param employeeDto
     * @return boolean
     */
    public boolean saveEmployee(EmployeeDto employeeDto) {
        try {
            Optional<Employee> employeeExists = employeeRepository.findByEmployeeNumberAndActifTrue(employeeDto.getEmployeeNumber());
            Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
            // employee existant --> update
            if (employeeExists.isPresent()) {
                employee.setEmployeeId(employeeExists.get().getEmployeeId());
                employee.setEmployeeNumber(employeeExists.get().getEmployeeNumber());
            } else {
                employee.setEmployeeNumber(generateEmployeeNumber());
            }
            employeeRepository.save(employee);
            log.info("employee saved successfully!");
            return true;
        } catch (Exception e) {
            log.error("Exception in EmployeeService --> saveEmployee {}", e.getMessage());
        }
        return false;
    }

    /**
     * generate random unique number for employee
     *
     * @return String
     */
    private String generateEmployeeNumber() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    /**
     * delete employee logically (isActif = false)
     *
     * @param employeeNumber
     * @return boolean
     */
    public boolean deleteEmployee(String employeeNumber) {
        try {
            Optional<Employee> employeeExists = employeeRepository.findByEmployeeNumberAndActifTrue(employeeNumber);
            if (employeeExists.isPresent()) {
                employeeExists.get().setActif(false);
                employeeRepository.save(employeeExists.get());
                log.info("Employee deleted successfully");
                return true;
            }
            log.info("Employee does not exist");
        } catch (Exception e) {
            log.error("Exception in EmployeeService --> deleteEmployee {}", e.getMessage());
        }
        return false;
    }

    public List<EmployeeDto> searchEmployee(EmployeeFilter employeeFilter) {
        List<EmployeeDto> result = employeeMapper.employeeToEmployeeDto(employeeRepository.findByActifTrue());
        try {
            if (!StringUtils.isEmpty(employeeFilter.firstname())) {
                result = result.stream().filter(employeeDto -> compareStringIgnoringCaseAndAccent(employeeDto.getFirstname(), employeeFilter.firstname(), false)).toList();
            }
            if (!StringUtils.isEmpty(employeeFilter.lastname())) {
                result = result.stream().filter(employeeDto -> compareStringIgnoringCaseAndAccent(employeeDto.getLastname(), employeeFilter.lastname(), false)).toList();
            }
            if (!StringUtils.isEmpty(employeeFilter.email())) {
                result = result.stream().filter(employeeDto -> compareStringIgnoringCaseAndAccent(employeeDto.getEmail(), employeeFilter.email(), false)).toList();
            }
            if (employeeFilter.typeEmployee() != null) {
                result = result.stream().filter(employeeDto -> employeeDto.getTypeEmployee().equals(employeeFilter.typeEmployee())).toList();
            }
            if (employeeFilter.experience() != 0) {
                result = result.stream().filter(employeeDto -> employeeDto.getExperience() == employeeFilter.experience()).toList();
            }
            if (employeeFilter.typeTechnology() != null) {
                result = result.stream().filter(employeeDto -> employeeDto.getTechnologies().stream().anyMatch(
                        typeTechnology -> typeTechnology.getType().equals(employeeFilter.typeTechnology()))
                ).toList();
            }
            if (!employeeFilter.technologies().isEmpty()) {
                result = result.stream().filter(employeeDto -> employeeDto.getTechnologies().stream().anyMatch(
                        technology -> employeeFilter.technologies().contains(technology.getName())
                )).toList();
            }
            return result;
        } catch (Exception e) {
            log.error("Exception in EmployeeService --> searchEmployee {}", e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * @param str1
     * @param str2
     * @param strictComparison : if true equals, else contains
     * @return boolean
     */
    private boolean compareStringIgnoringCaseAndAccent(String str1, String str2, boolean strictComparison) {
        if (str1 == null || str2 == null) {
            return false;
        }
        // Normalize is used for accents
        if (strictComparison) {
            return Normalizer.normalize(str1, Normalizer.Form.NFKD).equalsIgnoreCase(Normalizer.normalize(str2, Normalizer.Form.NFKD));
        } else {
            return Normalizer.normalize(str1, Normalizer.Form.NFKD).toLowerCase().contains(Normalizer.normalize(str2, Normalizer.Form.NFKD).toLowerCase());
        }
    }

    public boolean assignMissionToEmployee(Integer missionId, String employeeNumber) {
        try {
            Optional<Employee> employeeExists = employeeRepository.findByEmployeeNumberAndActifTrue(employeeNumber);
            Optional<Mission> missionExists = missionRepository.findById(missionId);
            if (employeeExists.isPresent() && missionExists.isPresent()) {
                List<Mission> missions = employeeExists.get().getMissions();
                missions.add(missionExists.get());
                employeeExists.get().setMissions(missions);
                employeeExists.get().setInMission(true);
                employeeRepository.save(employeeExists.get());
                log.info("mission assigned successfully to employee {}", employeeExists.get());
                return true;
            }
            log.info("mission or employee not found, can not assign mission to employee!");
        } catch (Exception e) {
            log.error("Exception in EmployeeService --> assignMissionToEmployee {}", e.getMessage());
        }
        return false;
    }

    public boolean assignTechnologiesToEmployee(List<Technology> technologies, String employeeNumber, boolean withCrush) {
        try {
            Optional<Employee> employeeExists = employeeRepository.findByEmployeeNumberAndActifTrue(employeeNumber);
            if (employeeExists.isPresent()) {
                // assign new technologies to employee by deleting old ones
                if (withCrush) {
                    employeeExists.get().setTechnologies(technologies);
                } else {
                    // add new technologies to existing technologies
                    List<Technology> newTechnologies = employeeExists.get().getTechnologies();
                    newTechnologies.addAll(technologies);
                    employeeExists.get().setTechnologies(newTechnologies);
                }
                employeeRepository.save(employeeExists.get());
                log.info("technologies assigned to employee successfully {}", employeeExists.get());
                return true;
            }
        } catch (Exception e) {
            log.error("Exception in EmployeeService --> assignTechnologiesToEmployee {}", e.getMessage());
        }
        return false;
    }

}

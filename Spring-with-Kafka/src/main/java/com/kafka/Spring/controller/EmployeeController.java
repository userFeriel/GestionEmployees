package com.kafka.Spring.controller;

import com.kafka.Spring.employe.dto.EmployeeDto;
import com.kafka.Spring.employe.service.EmployeeService;
import com.kafka.Spring.employe.utils.EmployeeFilter;
import com.kafka.Spring.technology.entity.Technology;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth/employee/")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("findAll")
    ResponseEntity<List<EmployeeDto>> getAllEmloyees() {
        return new ResponseEntity<>(employeeService.findAllEmployees(), HttpStatus.OK);
    }

    @PostMapping("saveEmployee")
    ResponseEntity<Boolean> saveEmployee(@RequestBody @NotNull @Valid EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.OK);
    }

    @PutMapping("deleteEmployee/{employeeNumber}")
    ResponseEntity<Boolean> deleteEmployee(@PathVariable @NotNull String employeeNumber) {
        return new ResponseEntity<>(employeeService.deleteEmployee(employeeNumber), HttpStatus.OK);
    }

    @PostMapping("filterEmployee")
    ResponseEntity<List<EmployeeDto>> filterEmployee(@RequestBody @NotNull EmployeeFilter employeeFilter) {
        return new ResponseEntity<>(employeeService.searchEmployee(employeeFilter), HttpStatus.OK);
    }

    @GetMapping("assignMissionToEmployee/{missionId}/{employeeNumber}")
    ResponseEntity<Boolean> assignMissionToEmployee(@PathVariable @NotNull Integer missionId, @PathVariable @NotNull String employeeNumber){
        return new ResponseEntity<>(employeeService.assignMissionToEmployee(missionId, employeeNumber), HttpStatus.OK);
    }

    @PostMapping("assignTechnologiesToEmployee/{employeeNumber}/{withCrush}")
    ResponseEntity<Boolean> assignTechnologiesToEmployee(@RequestBody @NotNull List<Technology> technologies, @PathVariable @NotNull String employeeNumber, @PathVariable  boolean withCrush) {
        return new ResponseEntity<>(employeeService.assignTechnologiesToEmployee(technologies, employeeNumber, withCrush), HttpStatus.OK);
    }
}

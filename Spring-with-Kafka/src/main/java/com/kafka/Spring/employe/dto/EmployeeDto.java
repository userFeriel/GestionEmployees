package com.kafka.Spring.employe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kafka.Spring.employe.entity.TypeEmployee;
import com.kafka.Spring.mission.entity.Mission;
import com.kafka.Spring.technology.entity.Technology;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    private String employeeNumber;
    @NotBlank
    private String email;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private boolean inMission;
    private boolean actif;
    private int experience;
    private TypeEmployee typeEmployee;
    private List<Technology> technologies;
    List<Mission> missions;
}

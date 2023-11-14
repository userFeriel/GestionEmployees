package com.kafka.Spring.employe.utils;

import com.kafka.Spring.employe.entity.TypeEmployee;
import com.kafka.Spring.technology.entity.Technology;
import com.kafka.Spring.technology.entity.TypeTechnology;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public record EmployeeFilter(String firstname,
                             String lastname,
                             String email,
                             int experience,
                             TypeEmployee typeEmployee,
                             List<String> technologies,
                             TypeTechnology typeTechnology
) {
}

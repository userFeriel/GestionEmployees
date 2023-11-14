package com.kafka.Spring.employe.mapper;

import com.kafka.Spring.employe.dto.EmployeeDto;
import com.kafka.Spring.employe.entity.Employee;
import com.kafka.Spring.role.mapper.RoleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EmployeeMapper {
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);
    EmployeeDto employeeToEmployeeDto(Employee employee);
    List<Employee> employeeDtoToEmployee(List<EmployeeDto> employeeDto);
    List<EmployeeDto> employeeToEmployeeDto(List<Employee> employee);
}

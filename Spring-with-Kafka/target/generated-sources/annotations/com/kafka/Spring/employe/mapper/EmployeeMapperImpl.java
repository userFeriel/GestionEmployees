package com.kafka.Spring.employe.mapper;

import com.kafka.Spring.employe.dto.EmployeeDto;
import com.kafka.Spring.employe.entity.Employee;
import com.kafka.Spring.mission.entity.Mission;
import com.kafka.Spring.technology.entity.Technology;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-14T14:29:08+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setFirstname( employeeDto.getFirstname() );
        employee.setLastname( employeeDto.getLastname() );
        employee.setEmployeeNumber( employeeDto.getEmployeeNumber() );
        employee.setEmail( employeeDto.getEmail() );
        employee.setBirthDate( employeeDto.getBirthDate() );
        employee.setInMission( employeeDto.isInMission() );
        employee.setActif( employeeDto.isActif() );
        employee.setExperience( employeeDto.getExperience() );
        employee.setTypeEmployee( employeeDto.getTypeEmployee() );
        List<Technology> list = employeeDto.getTechnologies();
        if ( list != null ) {
            employee.setTechnologies( new ArrayList<Technology>( list ) );
        }
        List<Mission> list1 = employeeDto.getMissions();
        if ( list1 != null ) {
            employee.setMissions( new ArrayList<Mission>( list1 ) );
        }

        return employee;
    }

    @Override
    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setFirstname( employee.getFirstname() );
        employeeDto.setLastname( employee.getLastname() );
        employeeDto.setEmployeeNumber( employee.getEmployeeNumber() );
        employeeDto.setEmail( employee.getEmail() );
        employeeDto.setBirthDate( employee.getBirthDate() );
        employeeDto.setInMission( employee.isInMission() );
        employeeDto.setActif( employee.isActif() );
        employeeDto.setExperience( employee.getExperience() );
        employeeDto.setTypeEmployee( employee.getTypeEmployee() );
        List<Technology> list = employee.getTechnologies();
        if ( list != null ) {
            employeeDto.setTechnologies( new ArrayList<Technology>( list ) );
        }
        List<Mission> list1 = employee.getMissions();
        if ( list1 != null ) {
            employeeDto.setMissions( new ArrayList<Mission>( list1 ) );
        }

        return employeeDto;
    }

    @Override
    public List<Employee> employeeDtoToEmployee(List<EmployeeDto> employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        List<Employee> list = new ArrayList<Employee>( employeeDto.size() );
        for ( EmployeeDto employeeDto1 : employeeDto ) {
            list.add( employeeDtoToEmployee( employeeDto1 ) );
        }

        return list;
    }

    @Override
    public List<EmployeeDto> employeeToEmployeeDto(List<Employee> employee) {
        if ( employee == null ) {
            return null;
        }

        List<EmployeeDto> list = new ArrayList<EmployeeDto>( employee.size() );
        for ( Employee employee1 : employee ) {
            list.add( employeeToEmployeeDto( employee1 ) );
        }

        return list;
    }
}

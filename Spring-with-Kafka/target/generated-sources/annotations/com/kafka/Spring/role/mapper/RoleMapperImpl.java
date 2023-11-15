package com.kafka.Spring.role.mapper;

import com.kafka.Spring.role.dto.RoleDto;
import com.kafka.Spring.role.entity.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-14T14:29:09+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto sourceToDestination(Role source) {
        if ( source == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setRoleName( source.getRoleName() );
        roleDto.setRoleDescription( source.getRoleDescription() );

        return roleDto;
    }

    @Override
    public Role destinationToSource(RoleDto destination) {
        if ( destination == null ) {
            return null;
        }

        Role role = new Role();

        role.setRoleName( destination.getRoleName() );
        role.setRoleDescription( destination.getRoleDescription() );

        return role;
    }
}

package com.kafka.Spring.role.mapper;

import org.mapstruct.Mapper;

import com.kafka.Spring.role.dto.RoleDto;
import com.kafka.Spring.role.entity.Role;
;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	 RoleDto sourceToDestination(Role source);
	 Role destinationToSource(RoleDto destination);
}

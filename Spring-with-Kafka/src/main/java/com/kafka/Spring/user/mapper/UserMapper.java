package com.kafka.Spring.user.mapper;

import com.kafka.Spring.role.mapper.RoleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kafka.Spring.user.dto.UserDto;
import com.kafka.Spring.user.entity.User;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
		uses = RoleMapper.class
)
public interface  UserMapper {

	UserDto sourceToDestination(User source);
	List<UserDto> sourceToDestination(List<User> source);
	User destinationToSource(UserDto destination);
	List<User> destinationToSource(List<UserDto> destination);





}

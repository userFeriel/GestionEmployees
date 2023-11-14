package com.kafka.Spring.user.mapper;

import com.kafka.Spring.role.dto.RoleDto;
import com.kafka.Spring.role.entity.Role;
import com.kafka.Spring.role.mapper.RoleMapper;
import com.kafka.Spring.user.dto.UserDto;
import com.kafka.Spring.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-14T11:53:53+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDto sourceToDestination(User source) {
        if ( source == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUsername( source.getUsername() );
        userDto.setFirstname( source.getFirstname() );
        userDto.setLastname( source.getLastname() );
        userDto.setEmail( source.getEmail() );
        userDto.setPassword( source.getPassword() );
        userDto.setAge( source.getAge() );
        userDto.setActif( source.isActif() );
        userDto.setCanAccess( source.isCanAccess() );
        userDto.setRoles( roleListToRoleDtoList( source.getRoles() ) );

        return userDto;
    }

    @Override
    public List<UserDto> sourceToDestination(List<User> source) {
        if ( source == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( source.size() );
        for ( User user : source ) {
            list.add( sourceToDestination( user ) );
        }

        return list;
    }

    @Override
    public User destinationToSource(UserDto destination) {
        if ( destination == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( destination.getUsername() );
        user.setFirstname( destination.getFirstname() );
        user.setLastname( destination.getLastname() );
        user.setEmail( destination.getEmail() );
        user.setPassword( destination.getPassword() );
        user.setAge( destination.getAge() );
        user.setActif( destination.isActif() );
        user.setCanAccess( destination.isCanAccess() );
        user.setRoles( roleDtoListToRoleList( destination.getRoles() ) );

        return user;
    }

    @Override
    public List<User> destinationToSource(List<UserDto> destination) {
        if ( destination == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( destination.size() );
        for ( UserDto userDto : destination ) {
            list.add( destinationToSource( userDto ) );
        }

        return list;
    }

    protected List<RoleDto> roleListToRoleDtoList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleDto> list1 = new ArrayList<RoleDto>( list.size() );
        for ( Role role : list ) {
            list1.add( roleMapper.sourceToDestination( role ) );
        }

        return list1;
    }

    protected List<Role> roleDtoListToRoleList(List<RoleDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Role> list1 = new ArrayList<Role>( list.size() );
        for ( RoleDto roleDto : list ) {
            list1.add( roleMapper.destinationToSource( roleDto ) );
        }

        return list1;
    }
}

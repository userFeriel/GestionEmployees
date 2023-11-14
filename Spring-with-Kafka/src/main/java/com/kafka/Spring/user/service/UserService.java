package com.kafka.Spring.user.service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.kafka.Spring.kafka.model.Historique;
import com.kafka.Spring.kafka.model.TYPE_ACTION;
import com.kafka.Spring.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafka.Spring.role.dto.RoleDto;
import com.kafka.Spring.role.entity.Role;
import com.kafka.Spring.role.repository.RoleRepository;
import com.kafka.Spring.user.dto.UserDto;
import com.kafka.Spring.user.entity.User;
import com.kafka.Spring.user.mapper.UserMapper;
import com.kafka.Spring.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository RoleRepository;

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    private UserMapper userMapper;




    public UserService(UserRepository userRepository, RoleRepository roleRepository, KafkaProducerService kafkaProducerService) {
        this.userRepository = userRepository;
        this.RoleRepository = roleRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    /**
     * add or update user
     * @param userDto
     * @return boolean
     */
    public boolean saveUser(UserDto userDto) {
        try {
            Optional<User> foundUser = userRepository.findByUsername(userMapper.destinationToSource(userDto).getUsername());
            User user = userMapper.destinationToSource(userDto);
            TYPE_ACTION typeAction = null;
            if (foundUser.isPresent()) {
                // user already exists (update user)
                user.setUserId(foundUser.get().getUserId());
                List<Role> roles = RoleRepository.findAllByRoleNameInAndCanBeAddedTrue(user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()));
                user.setRoles(roles);
                typeAction = TYPE_ACTION.UPDATE_USER;
            } else {
                //TODO new user --> create random psw
                user.setPassword(generateRandomPsw());
                typeAction = TYPE_ACTION.CREATE_USER;
            }
            userRepository.save(user);

            log.info("user saved successfully!");
            kafkaProducerService.sendHistoriqueData(typeAction, LocalDate.now(), userDto);
            
            return true;
        } catch (Exception e) {
            log.error("Exception in UserService --> addUser {}", e.getMessage());
        }
        return false;

    }

 
    private String generateRandomPsw() {
        return "abcdef124532";
    }

    /**
     * delete user from database
     * @param username
     * @return boolean
     */

    public boolean deleteUser(String username) {
        try {
            Optional<User> foundUser = userRepository.findByUsername(username);
            if (!foundUser.isPresent()) {
                // error user does not exist
                log.error("can not delete user,  user does not exist");
                return false;
            } else {
                userRepository.delete(foundUser.get());
                log.info("user deleted successfully");
                kafkaProducerService.sendHistoriqueData(TYPE_ACTION.DELETE_USER, LocalDate.now(), foundUser.get());
                return true;
            }
        } catch (Exception e) {
            log.error("Exception in UserService --> deleteUser {}", e.getMessage());
        }
        return false;
    }

    /**
     * assign roles to user
     * @param rolesDto
     * @param userId
     * @return boolean
     */
    public boolean assignRoleToUser(List<RoleDto> rolesDto, Integer userId) {
        try {
            Optional<User> foundUser = userRepository.findById(userId);

            if (foundUser.isPresent()) {
                log.info("user found {}", foundUser.get());
                List<Role> roles = RoleRepository.findAllByRoleNameInAndCanBeAddedTrue(rolesDto.stream().map(RoleDto::getRoleName).collect(Collectors.toList()));
                foundUser.get().setRoles(roles);
                userRepository.save(foundUser.get());
                HashMap<Integer, Object> historiqueData = new HashMap<>();
                historiqueData.put(userId, rolesDto);
                kafkaProducerService.sendHistoriqueData(TYPE_ACTION.ASSIGN_ROLE_TO_USER, LocalDate.now(), historiqueData);
                log.info("roles assigned to user successfully");
                return true;
            }
        } catch (Exception e) {
            log.error("Exception in UserService --> assignRoleToUser {}", e.getMessage());
        }
        return false;
    }

    /**
     * get all users
     * @return List<UserDto>
     */
    public List<UserDto> getAllUsers() {
        try {
            return userMapper.sourceToDestination(userRepository.findAll());
        } catch (Exception e) {
            log.error("Exception in UserService --> getAllUsers {}", e.getMessage());
        }
        return new ArrayList<>();
    }

}

package com.kafka.Spring.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kafka.Spring.role.dto.RoleDto;
import com.kafka.Spring.user.dto.UserDto;
import com.kafka.Spring.user.service.UserService;

@RestController
@RequestMapping("api/auth/user/")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("saveUser")
    public ResponseEntity<Boolean> addUser(@RequestBody @NotNull @Valid UserDto userDto) {
        return new ResponseEntity<Boolean>(userService.saveUser(userDto), HttpStatus.OK);
    }

    @PostMapping("assignRoles/{userId}")
    public ResponseEntity<Boolean> assignRolesToUser(@RequestBody @NotNull List<RoleDto> rolesDto, @PathVariable @NotNull Integer userId) {
        return new ResponseEntity<Boolean>(userService.assignRoleToUser(rolesDto, userId), HttpStatus.OK);
    }

    @PutMapping("deleteUser/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable @NotNull String username) {
        return new ResponseEntity<Boolean>(userService.deleteUser(username), HttpStatus.OK);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<UserDto>> findAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}

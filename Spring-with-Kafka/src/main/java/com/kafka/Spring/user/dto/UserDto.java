package com.kafka.Spring.user.dto;



import com.kafka.Spring.role.dto.RoleDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	@NotBlank
	private String username;
	@NotBlank
	private String firstname;
	@NotBlank
	private String lastname;
	@NotBlank
	private String email;
	private String password;
	private int age;
	private boolean actif;
	private boolean canAccess;
	private List<RoleDto> roles;

	
}

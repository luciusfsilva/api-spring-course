package com.network.apispringcourse.dto;

import javax.validation.constraints.NotNull;

import com.network.apispringcourse.enums.Role;

import lombok.Data;

@Data
public class UserUpdateRoleDto {
	
	@NotNull(message = "Role required")
	private Role role;

}

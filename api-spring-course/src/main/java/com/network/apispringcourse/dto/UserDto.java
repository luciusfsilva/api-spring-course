package com.network.apispringcourse.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	@Email(message = "Invalid email address")
	private String email;
	
	@NotBlank(message = "Password required")
	private String password;
	
	

}

package com.network.apispringcourse.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.network.apispringcourse.domain.Request;
import com.network.apispringcourse.domain.RequestStage;
import com.network.apispringcourse.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
	
	@NotBlank(message = "Name required")
	private String name;
	
	@Email(message = "Invalid email address")
	private String email;
	
	@Size(min = 7, max = 99, message = "Password must between 7 and 99")
	private String password;
	
	private List<Request> requests;
	
	private List<RequestStage> stages;
	
	public User transformToUser() {
		User user = new User(null, this.name, this.email, this.password, null,
				this.requests, this.stages);
		return user;
	}

}

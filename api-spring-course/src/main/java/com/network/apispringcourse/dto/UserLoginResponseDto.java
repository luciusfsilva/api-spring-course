package com.network.apispringcourse.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponseDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String token;
	private Long expireIn;
	private String tokenProvider;
	
	

}

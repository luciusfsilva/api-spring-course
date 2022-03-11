package com.network.apispringcourse.repository.exception;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int code;
	private String message;
	private Date date;

}

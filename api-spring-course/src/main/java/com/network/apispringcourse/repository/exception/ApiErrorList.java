package com.network.apispringcourse.repository.exception;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorList extends ApiError {

	private static final long serialVersionUID = 1L;
	
	private List<String> errors;
	
	public ApiErrorList(int code, String message, Date date, List<String> errors) {
		super(code, message, date);
		this.errors = errors;
	}

}

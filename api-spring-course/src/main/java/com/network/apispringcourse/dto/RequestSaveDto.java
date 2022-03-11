package com.network.apispringcourse.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.network.apispringcourse.domain.Request;
import com.network.apispringcourse.domain.RequestStage;
import com.network.apispringcourse.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSaveDto {
	
	@NotBlank(message = "Subject required")
	private String subject;
	
	private String description;
	@NotNull(message = "Owner required")
	private User owner;
	private List<RequestStage> stages;
	
	public Request transformToRequest() {
		Request request = new Request(null, this.subject, this.description, null,
				null, this.owner, this.stages);
		return request;
	}

}

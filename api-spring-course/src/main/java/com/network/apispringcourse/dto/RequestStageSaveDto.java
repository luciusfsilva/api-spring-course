package com.network.apispringcourse.dto;

import javax.validation.constraints.NotNull;

import com.network.apispringcourse.domain.Request;
import com.network.apispringcourse.domain.RequestStage;
import com.network.apispringcourse.domain.User;
import com.network.apispringcourse.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestStageSaveDto {
	
	private String description;
	
	@NotNull(message = "State required")
	private RequestState state;
	
	@NotNull(message = "Request required")
	private Request request;
	
	@NotNull(message = "Owner required")
	private User owner;
	
	public RequestStage transformToRequestStage() {
		RequestStage requestStage = new RequestStage(null, this.description, null, this.state,
				this.request, this.owner);
		return requestStage;
	}

}

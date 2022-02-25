package com.network.apispringcourse.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.network.apispringcourse.domain.RequestStage;
import com.network.apispringcourse.enums.RequestState;
import com.network.apispringcourse.repository.RequestRepository;
import com.network.apispringcourse.repository.RequestStageRepository;

@Service
public class RequestStageService {

	@Autowired
	private RequestStageRepository repository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	public RequestStage save(RequestStage stage) {
		stage.setRealizationDate(new Date());
		
		RequestStage createdRequestStage = repository.save(stage);
		
		Long requestId = stage.getRequest().getId();
		RequestState state = stage.getState();
		
		requestRepository.updateStatus(requestId, state);
		
		return createdRequestStage;
	}
	
	public RequestStage getById(Long id) {
		RequestStage result = repository.findById(id).get();
		return result;
	}
	
	public List<RequestStage> listAllByOwnerId(Long requestId) {
		List<RequestStage> stages = repository.findAllByRequestId(requestId);
		return stages;
	}
	
	
}

package com.network.apispringcourse.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.network.apispringcourse.domain.RequestStage;
import com.network.apispringcourse.enums.RequestState;
import com.network.apispringcourse.exception.NotFoundException;
import com.network.apispringcourse.model.PageModel;
import com.network.apispringcourse.model.PageRequestModel;
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
		if (ObjectUtils.isEmpty(result)) {
			new NotFoundException("There are not request stage with id = " + id);
		}
		return result;
	}
	
	public List<RequestStage> listAllByOwnerId(Long requestId) {
		List<RequestStage> stages = repository.findAllByRequestId(requestId);
		return stages;
	}
	
	public PageModel<RequestStage> listAllByRequestOnLazyMode(Long ownerId, PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<RequestStage> page = repository.findAllByRequestId(ownerId, pageable);
		
		PageModel<RequestStage> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
	
		return pm;
	}
	
	
}

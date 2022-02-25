package com.network.apispringcourse.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.network.apispringcourse.domain.Request;
import com.network.apispringcourse.enums.RequestState;
import com.network.apispringcourse.repository.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository repository;
	
	public Request save(Request request) {
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		
		Request createdRequest = repository.save(request);
		return createdRequest;
	}
	
	public Request update(Request request) {
		Request updatedRequest = repository.save(request);
		return updatedRequest;
	}
	
	public Request getById(Long id) {
		Request result = repository.findById(id).get();
		return result;
	}
	
	public List<Request> listAll() {
		List<Request> requests = repository.findAll();
		return requests;
	}
	
	public List<Request> listAllByOwnerId(Long ownerId) {
		List<Request> requests = repository.findAllByOwnerId(ownerId);
		return requests;
	}
	
}

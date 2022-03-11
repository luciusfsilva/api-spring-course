package com.network.apispringcourse.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.network.apispringcourse.domain.Request;
import com.network.apispringcourse.enums.RequestState;
import com.network.apispringcourse.exception.NotFoundException;
import com.network.apispringcourse.model.PageModel;
import com.network.apispringcourse.model.PageRequestModel;
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
		if (ObjectUtils.isEmpty(result)) {
			new NotFoundException("There are not request with id = " + id);
		}
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
	
	public PageModel<Request> listAllOnLazyMode(Long ownerId, PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<Request> page = repository.findAllByOwnerId(ownerId, pageable);
		
		PageModel<Request> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
	
		return pm;
	}
	
	public PageModel<Request> listAllOnLazyMode(PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<Request> page = repository.findAll(pageable);
		
		PageModel<Request> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
	
		return pm;
	}
}

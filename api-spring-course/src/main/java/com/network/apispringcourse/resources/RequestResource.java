package com.network.apispringcourse.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.apispringcourse.domain.Request;
import com.network.apispringcourse.domain.RequestStage;
import com.network.apispringcourse.service.RequestService;
import com.network.apispringcourse.service.RequestStageService;

@RestController
@RequestMapping("requests")
public class RequestResource {
	
	@Autowired
	private RequestService service;
	
	@Autowired
	private RequestStageService serviceStage;
	
	@PostMapping
	public ResponseEntity<Request> save(@RequestBody Request request){
		Request createdRequest = service.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable(name = "id") Long id, @RequestBody Request request){
		request.setId(id);
		Request updatedRequest = service.update(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedRequest);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable(name = "id") Long id){
		Request request = service.getById(id);
		return ResponseEntity.status(HttpStatus.OK).body(request);
	}
	
	@GetMapping
	public ResponseEntity<List<Request>> listAll(){
		List<Request> requests = service.listAll();
		return ResponseEntity.status(HttpStatus.OK).body(requests);
	}
	
	@GetMapping("/{id}/request-stages")
	public ResponseEntity<List<RequestStage>> listAllStagesById(@PathVariable(name = "id") Long id){
		List<RequestStage> stages = serviceStage.listAllByOwnerId(id);
		return ResponseEntity.status(HttpStatus.OK).body(stages);
	}

}

package com.network.apispringcourse.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.apispringcourse.domain.RequestStage;
import com.network.apispringcourse.service.RequestStageService;

@RestController
@RequestMapping("request-stages")
public class RequestStageResource {

	@Autowired
	private RequestStageService service;
	
	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody RequestStage stage){
		RequestStage createdStage = service.save(stage);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdStage);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable(name = "id") Long id){
		RequestStage stage = service.getById(id);
		return ResponseEntity.status(HttpStatus.OK).body(stage);
	}
}
package com.network.apispringcourse.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.network.apispringcourse.domain.Request;
import com.network.apispringcourse.domain.RequestStage;
import com.network.apispringcourse.dto.RequestSaveDto;
import com.network.apispringcourse.dto.RequestUpdateDto;
import com.network.apispringcourse.model.PageModel;
import com.network.apispringcourse.model.PageRequestModel;
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
	public ResponseEntity<Request> save(@RequestBody @Valid RequestSaveDto requestDto){
		Request request = requestDto.transformToRequest();
		Request createdRequest = service.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable(name = "id") Long id, @RequestBody @Valid RequestUpdateDto requestDto){
		Request request = requestDto.transformToRequest();
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
	public ResponseEntity<PageModel<Request>> listAll(@RequestParam(value="page", defaultValue = "0") int page, @RequestParam(value="size", defaultValue = "1") int size){
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = service.listAllOnLazyMode(pr);
		return ResponseEntity.status(HttpStatus.OK).body(pm);
		
	}
	
	@GetMapping("/{id}/request-stages")
	public ResponseEntity<PageModel<RequestStage>> listAllStagesById(@PathVariable(name = "id") Long id, @RequestParam(value="page", defaultValue = "0") int page, @RequestParam(value="size", defaultValue = "1") int size){
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<RequestStage> pm = serviceStage.listAllByRequestOnLazyMode(id, pr);
		return ResponseEntity.status(HttpStatus.OK).body(pm);
	}

}

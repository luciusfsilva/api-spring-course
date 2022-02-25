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
import com.network.apispringcourse.domain.User;
import com.network.apispringcourse.dto.UserDto;
import com.network.apispringcourse.service.RequestService;
import com.network.apispringcourse.service.UserService;

@RestController
@RequestMapping("users")
public class UserResources {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private RequestService serviceRequest;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user){
		User createdUser = service.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody User user){
		user.setId(id);
		User updatedUser = service.update(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable(name = "id") Long id){
		User user = service.getById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listAll(){
		List<User> users = service.listAll();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserDto user){
		User loggedUser = service.login(user.getEmail(), user.getPassword());
		return ResponseEntity.status(HttpStatus.OK).body(loggedUser);
	}
	
	@GetMapping("/{id}/requests")
	public ResponseEntity<List<Request>> listAllRequestsById(@PathVariable(name = "id") Long id){
		List<Request> requests = serviceRequest.listAllByOwnerId(id);
		return ResponseEntity.status(HttpStatus.OK).body(requests);
	}
	

}

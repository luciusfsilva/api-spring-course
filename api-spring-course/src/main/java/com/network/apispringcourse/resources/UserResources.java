package com.network.apispringcourse.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.network.apispringcourse.domain.Request;
import com.network.apispringcourse.domain.User;
import com.network.apispringcourse.dto.UserDto;
import com.network.apispringcourse.dto.UserLoginResponseDto;
import com.network.apispringcourse.dto.UserSaveDto;
import com.network.apispringcourse.dto.UserUpdateDto;
import com.network.apispringcourse.dto.UserUpdateRoleDto;
import com.network.apispringcourse.model.PageModel;
import com.network.apispringcourse.model.PageRequestModel;
import com.network.apispringcourse.security.JwtManager;
import com.network.apispringcourse.service.RequestService;
import com.network.apispringcourse.service.UserService;

@RestController
@RequestMapping("users")
public class UserResources {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private RequestService serviceRequest;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtManager jwtManager;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid UserSaveDto userDto){
		User userToSave = userDto.transformToUser();
		User createdUser = service.save(userToSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody @Valid UserUpdateDto userDto){
		User userToUpdate = userDto.transformToUser();
		userToUpdate.setId(id);
		User updatedUser = service.update(userToUpdate);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable(name = "id") Long id){
		User user = service.getById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping
	public ResponseEntity<PageModel<User>> listAll(@RequestParam(value="page", defaultValue = "0") int page, @RequestParam(value="size", defaultValue = "1") int size){
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = service.listAllOnLazyMode(pr);
		
		return ResponseEntity.status(HttpStatus.OK).body(pm);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserDto user){
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		Authentication auth =  authManager.authenticate(token);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		org.springframework.security.core.userdetails.User userSpring = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		
		String email = userSpring.getUsername();
		List<String> roles = userSpring.getAuthorities().stream().map(autorities -> autorities.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(jwtManager.createToker(email, roles));
	}
	
	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> listAllRequestsById(@RequestParam(value="page", defaultValue = "0") int page, @RequestParam(value="size", defaultValue = "1") int size, @PathVariable(name = "id") Long id){
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = serviceRequest.listAllOnLazyMode(id, pr);
		return ResponseEntity.status(HttpStatus.OK).body(pm);
	}
	
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody @Valid UserUpdateRoleDto user){
		User u = new User();
		u.setId(id);
		u.setRole(user.getRole());
		service.updateRole(u);
		return ResponseEntity.ok().build();
	}
	
	

}

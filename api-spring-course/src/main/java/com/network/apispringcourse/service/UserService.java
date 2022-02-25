package com.network.apispringcourse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.network.apispringcourse.domain.User;
import com.network.apispringcourse.repository.UserRepository;
import com.network.apispringcourse.util.HashUtil;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User save(User user) {
		
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);

		User createdUser = repository.save(user);
		return createdUser;
	}
	
	public User update(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		
		User UpdatedUser = repository.save(user);
		return UpdatedUser;
	}
	
	public User getById(Long id) {
		User result = repository.findById(id).get();
		return result;
	}
	
	public List<User> listAll(){
		List<User> users = repository.findAll();
		return users;
	}
	
	public User login(String email, String password) {
		password = HashUtil.getSecureHash(password);
		
		User result = repository.login(email, password).get();
		return result;
	}

}

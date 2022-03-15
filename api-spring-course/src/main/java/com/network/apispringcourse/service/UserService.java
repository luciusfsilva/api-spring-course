package com.network.apispringcourse.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.network.apispringcourse.domain.User;
import com.network.apispringcourse.exception.NotFoundException;
import com.network.apispringcourse.model.PageModel;
import com.network.apispringcourse.model.PageRequestModel;
import com.network.apispringcourse.repository.UserRepository;
import com.network.apispringcourse.util.HashUtil;

@Service
public class UserService implements UserDetailsService{
	
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
		if (ObjectUtils.isEmpty(result)) {
			new NotFoundException("There are not user with id = " + id);
		}
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
	
	public PageModel<User> listAllOnLazyMode(PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<User> page = repository.findAll(pageable);
		
		PageModel<User> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
	
		return pm;
	}
	
	public int updateRole(User user) {
		return repository.updateRole(user.getId(), user.getRole());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> result = repository.findByEmail(username);
		
		if (!result.isPresent()) {
			throw new UsernameNotFoundException("Dosen't exist user with email = " + username);
		}
		
		User user = result.get();
		List<GrantedAuthority> autorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
		org.springframework.security.core.userdetails.User userSpring = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), autorities);
		
		return userSpring;
	}

}

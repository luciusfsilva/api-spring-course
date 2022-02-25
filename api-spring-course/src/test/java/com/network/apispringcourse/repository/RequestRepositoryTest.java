package com.network.apispringcourse.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.network.apispringcourse.domain.Request;
import com.network.apispringcourse.domain.User;
import com.network.apispringcourse.enums.RequestState;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RequestRepositoryTest {
	
	@Autowired
	private RequestRepository repository;

	@Test
	public void AsaveTest() {
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request(null, "Novo Laptop HP", "Pretendo obter um laptop HP", new Date(), RequestState.OPEN, owner, null);
		
		Request createdRequest = repository.save(request);
		
		assertThat(createdRequest.getId()).isEqualTo(1L);
	}
	
	@Test
	public void updateTest() {
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request(1L, "Novo Laptop HP", "Pretendo obter um laptop HP, de RAM 16 GB", null, RequestState.OPEN, owner, null);
		
		Request updatedRequest = repository.save(request);
		
		assertThat(updatedRequest.getDescription()).isEqualTo("Pretendo obter um laptop HP, de RAM 16 GB");
		
	}
	
	@Test
	public void getByIdTest() {
	Request result = repository.findById(1L).get();
	
	assertThat(result).isNotNull();
		
	}
	
	@Test
	public void listTest() {
		List<Request> list = repository.findAllByOwnerId(1L);
		assertThat(list.size()).isEqualTo(1);
		
	}
	
	@Test
	public void updateStatusTest() {
		int affectRows = repository.updateStatus(1L, RequestState.PROGRESS);
		assertThat(affectRows).isEqualTo(1);
	}
	
	
}

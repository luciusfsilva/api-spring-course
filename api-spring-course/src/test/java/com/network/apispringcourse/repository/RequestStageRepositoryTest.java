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
import com.network.apispringcourse.domain.RequestStage;
import com.network.apispringcourse.domain.User;
import com.network.apispringcourse.enums.RequestState;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RequestStageRepositoryTest {
	
	@Autowired
	private RequestStageRepository repository;
	
	@Test
	public void AsaveTest() {
		Request request = new Request();
		request.setId(1L);
		
		User owner = new User();
		owner.setId(1L);
		
		RequestStage stage = new RequestStage(null, "Foi comprado um novo laptop de marca HD e com 16 GB de RAM", new Date(), RequestState.CLOSE, request, owner);
		
		RequestStage createdStage = repository.save(stage);
		
		assertThat(createdStage.getId()).isEqualTo(1L);
	}
	
	@Test
	public void getByIdTest() {
		RequestStage stage = repository.findById(1L).get();
		
		assertThat(stage.getDescription()).isEqualTo("Foi comprado um novo laptop de marca HD e com 16 GB de RAM");
	}
	
	@Test
	public void listByRequestIdTest() {
		List<RequestStage> stages = repository.findAllByRequestId(1L);
		
		assertThat(stages.size()).isEqualTo(2);
	}

}

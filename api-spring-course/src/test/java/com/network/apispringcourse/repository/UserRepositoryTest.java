package com.network.apispringcourse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.network.apispringcourse.domain.User;
import com.network.apispringcourse.enums.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repository;

	@Test
	public void AsaveTest() {
		User owner = new User(1L, "Kevin", "appjavaservertest52@yahoo.com", "123", Role.ADMINISTRATOR, null, null);
		User createowner = repository.save(owner);
		
		assertThat(createowner).isNotNull();
	}
	
	@Test
	public void updateTest() {
		User owner = new User(1L, "Kevin Wingi", "appjavaservertest52@yahoo.com", "123", Role.ADMINISTRATOR, null, null);
		User updatedUSer = repository.save(owner);
		
		assertThat(updatedUSer.getName()).isEqualTo("Kevin Wingi");
	}
	
	@Test
	public void getByIdTest() {
		User owner = repository.findById(1L).get();
		
		assertThat(owner.getPassword()).isEqualTo("123");
	}
	
	@Test
	public void listTest() {
		List<User> owners = repository.findAll();

		assertThat(owners.size()).isEqualTo(1);
	}
	
	@Test
	public void loginTest() {
		User owner = repository.login("appjavaservertest52@yahoo.com", "123").get();
		
		assertThat(owner.getId()).isEqualTo(1L);
	}
	
}

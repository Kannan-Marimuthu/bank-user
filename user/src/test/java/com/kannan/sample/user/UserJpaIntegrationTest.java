package com.kannan.sample.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kannan.sample.user.dao.UserRepo;
import com.kannan.sample.user.vo.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserJpaIntegrationTest {

	@Autowired
	private UserRepo userRepo;

	@Test
	public void retreiveUserEntityTest() {
		List<User> user = userRepo.findByUserName("Kannan");
		assertNotNull(user);
		assertEquals("Kannan", user.get(0).getUserName());
	}

	@Test
	public void retreiveUserListTest() {
		List<User> userList = (List<User>) userRepo.findAll();
		assertNotNull(userList);
		assertEquals(3, userList.size());
	}
}

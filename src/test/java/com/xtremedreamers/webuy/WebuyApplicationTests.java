package com.xtremedreamers.webuy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.xtremedreamers.webuy.controller.RedirectController;
import com.xtremedreamers.webuy.models.RegisteredUser;
import com.xtremedreamers.webuy.persistence.AdminDao;
import com.xtremedreamers.webuy.persistence.RegisteredUserDao;

@SpringBootTest
class WebuyApplicationTests {
	
	@Autowired
	RegisteredUserDao r;
	
	@Autowired
	AdminDao a;

	@Test
	void testFirstPage() {
		RedirectController admin = new RedirectController();
		String response = admin.ProductsList();
		assertThat(response).isEqualTo("login");
	}
	
	@Test
	void testLogin() {
		RedirectController admin = new RedirectController();
		String response = admin.getLogin();
		assertThat(response).isEqualTo("login");
	}
	
	@Test
	void testLoginAdmin() {
		RedirectController admin = new RedirectController();
		String response = admin.getLoginAdmin();
		assertThat(response).isEqualTo("loginadmin");
	}
	
	@Test
	void testRegister() {
		RedirectController admin = new RedirectController();
		String response = admin.getRegister();
		assertThat(response).isEqualTo("register");
	}
	
	@Test
	void testLookForUser() {
		RegisteredUser user = r.findUser("test@gmail.com", "test123");
		assertThat(user).isNotNull();
	}
	
	@Test
	void testCreateUser() {
		RegisteredUser instance = new RegisteredUser();
		instance.setEmail("junit@test.com"); 
		instance.setPassword("junit123"); 
		instance.setFullname("JUnit Test");
		instance.setAddress("Mexico"); 
		instance.setNumber("123456789");
		int result = r.createUser(instance);
		assertThat(result).isEqualTo(1);
	}

}

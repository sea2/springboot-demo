package com.yijiupi.login;

import com.yijiupi.login.entity.User;
import com.yijiupi.login.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApplicationTests {
	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void save(){
		User user=new User();
		user.setAge(21);
		user.setPassword("123456");
		user.setName("易酒批");
		user.setSex("女");
		userService.save(user);
	}

	@Test
	public void login(){
		User user=userService.getUser("张三","123456");
		Assert.assertEquals(new Integer(21),user.getAge());
	}

}

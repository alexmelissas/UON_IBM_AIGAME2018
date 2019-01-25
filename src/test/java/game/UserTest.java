package game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springboot.Application;
import springboot.domain.User;
import springboot.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {
	@Autowired
	private UserService userService;
	
	@Test
	public void getTest() {
		User user = userService.getUserById(1).get();
		assertTrue("char".equals(user.getUsername()));
		assertTrue("1234".equals(user.getPassword()));
		
		user = userService.getUserById(2).get();
		assertTrue("char".equals(user.getUsername()));
		assertTrue("12345".equals(user.getPassword()));
	}

	@Test
	public void updateTest() {
		User user = userService.getUserById(1).get();
		userService.updateUser(user.getId(), "eric", "4321");
		
		user = userService.getUserById(1).get();
		assertTrue("eric".equals(user.getUsername()));
		assertTrue("4321".equals(user.getPassword()));
		
		user = userService.getUserById(1).get();
		userService.updateUser(user.getId(), "char", "1234");
	}
}

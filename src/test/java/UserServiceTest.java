import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springboot.Application;
import springboot.domain.User;
import springboot.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {
	@Autowired
	private UserService userService;
	private String id;
	
	@Before
	public void addUserTest() {
		User user = new User();
		user.setUsername("eric");
		user.setPassword("1234");
		userService.addUser(user);
		id = user.getId();
	}
	
	@After
	public void deleteUserTest() {
		userService.deleteUserById(id);
	}
	
	@Test
	public void getUersTest() {
		List<User> users = (List<User>) userService.getAllUsers();
		assertTrue(users != null);
	}
	
	@Test
	public void updateUserTest() {
		User user = userService.getUserById(id);
		user.setPassword("4321");
		userService.updateUser(id, user);
	}
	
	@Test
	public void existTest() {
		assertTrue(userService.isExist("eric"));
	}
	
	@Test
	public void loginTest() {
		User user = new User();
		user.setUsername("eric");
		user.setPassword("1234");
		User result = userService.login(user);
		System.out.println(result);
	}
}

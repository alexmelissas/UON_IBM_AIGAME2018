package springboot.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.domain.User;
import springboot.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * Get all the users
	 * 
	 * @return
	 */
	@GetMapping("/users")
	public @ResponseBody Iterable<User> getAllUsers() {
		// TODO safety of access
		// Maybe unnecessary
		return userService.getAllUsers();
	}

	/**
	 * Add a new user and create account for users
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/users")
	public @ResponseBody String addUser(@RequestBody User user) {
		if (userService.isExist(user.getUsername())) {
			return "Username has been taken";
		}
		logger.info("======Account Creation======");
		userService.addUser(user);
		logger.info("======Account Creation Finished======");
		return "Saved";
	}

	/**
	 * Get a single user through id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/users/{id}")
	public @ResponseBody User getUserById(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
		if (!userService.isExistById(id)) {
			try {
				request.getRequestDispatcher("/404").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return userService.getUserById(id);
	}

	/**
	 * Update the information of users
	 * 
	 * @param id
	 * @param user
	 * @return
	 */
	@PutMapping("/users/{id}")
	public @ResponseBody String updateUser(@PathVariable String id, @RequestBody User user) {
		User previousUser = userService.getUserById(id);
		// check if modified username is exist
		if (userService.isExist(user.getUsername()) && !previousUser.getUsername().equals(user.getUsername())) {
			return "Username has been taken";
		}
		logger.info("======Account Update======");
		userService.updateUser(id, user);
		logger.info("======Account Update Finished======");
		return "Updated";
	}

	/**
	 * Delete the account of users
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/users/{id}")
	public @ResponseBody String deleteUser(@PathVariable String id) {
		userService.deleteUserById(id);
		return "Deleted";
	}

	/**
	 * Login
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/users/login")
	public User login(@RequestBody User user) {
		// TODO check auth => check ideal => check player => check username & password
		logger.info("======User Login======");
		User result = userService.login(user);
		logger.info("======User Login End======");
		return result;
	}
}

package springboot.web;

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

/**
 * <p>
 * The UserController class is used to handle the user related requests.
 * </p>
 * 
 * @author chenyu
 *
 */
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Get all the users
	 * 
	 * @return a list of users
	 */
	@GetMapping("/users")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * Add a new user and create account for users
	 * 
	 * @param user the new user
	 * @return the result
	 */
	@PostMapping("/users")
	public @ResponseBody String addUser(@RequestBody User user) {
		if (userService.isExist(user.getUsername())) {
			return "Username has been taken";
		}
		logger.info("======Account Creation======");
		userService.addUser(user);
		logger.info("======Account Creation End======");
		return "Saved";
	}

	/**
	 * Get a single user through id
	 * 
	 * @param id the id
	 * @return the user
	 */
	@GetMapping("/users/{id}")
	public @ResponseBody User getUserById(@PathVariable String id) {
		User user = userService.getUserById(id);
		return user;
	}

	/**
	 * Update the information of users
	 * 
	 * @param id the id
	 * @param user the user
	 * @return the result
	 */
	@PutMapping("/users/{id}")
	public @ResponseBody String updateUser(@PathVariable String id, @RequestBody User user) {
		if (!userService.isExistById(id)) {
			return "Failed";
		}
		User previousUser = userService.getUserById(id);
		// check if modified username is exist
		if (userService.isExist(user.getUsername()) && !previousUser.getUsername().equals(user.getUsername())) {
			return "Username has been taken";
		}
		logger.info("======Account Update======");
		userService.updateUser(id, user);
		logger.info("======Account Update End======");
		return "Updated";
	}

	/**
	 * Delete the account of users
	 * 
	 * @param id the id
	 * @return the result
	 */
	@DeleteMapping("/users/{id}")
	public @ResponseBody String deleteUser(@PathVariable String id) {
		userService.deleteUserById(id);
		return "Deleted";
	}

	/**
	 * Login
	 * 
	 * @param user the user
	 * @return the result
	 */
	@PostMapping("/users/login")
	public User login(@RequestBody User user) {
		logger.info("======User Login======");
		User result = userService.login(user);
		logger.info("======User Login End======");
		return result;
	}
}

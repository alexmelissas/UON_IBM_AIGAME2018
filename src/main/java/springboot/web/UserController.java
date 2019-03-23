package springboot.web;

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
		userService.addUser(user);
		return "Saved";
	}

	/**
	 * Get a single user through id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/users/{id}")
	public @ResponseBody User getUserById(@PathVariable String id) {
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
		userService.updateUser(id, user);
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
		return userService.login(user);
	}
}

package springboot.web;

import java.util.Optional;

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
	 * @return
	 */
	@GetMapping(path = "/users")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	/**
	 * Add a new user and create account for users 
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/users")
	public @ResponseBody String addUser(@RequestBody User user) {
		userService.addUser(user);
		return "Saved";
	}
	
	/**
	 * Get a single user through id
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/users/{id}")
	public @ResponseBody Optional<User> getUserById(@PathVariable String id) {
		return userService.getUserById(id);
	}
	
	/**
	 * Update the information of users
	 * @param id
	 * @param user
	 * @return
	 */
	@PutMapping(path = "/users/{id}")
	public @ResponseBody String updateUser(@PathVariable String id, @RequestBody User user) {
		userService.updateUser(id, user);
		return "Updated";
	}
	
	/**
	 * Delete the account of users
	 * @param id
	 * @return
	 */
	@DeleteMapping(path = "/users/{id}")
	public @ResponseBody String deleteUser(@PathVariable int id) {
		userService.deleteUserById(id);
		return "Deleted";
	}
}

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
		//TODO safety of access
		//Maybe unnecessary
		return userService.getAllUsers();
	}
	
	/**
	 * Add a new user and create account for users 
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/users")
	public @ResponseBody String addUser(@RequestBody User user) {
		if(userService.isExist(user.getUsername())) {
			return "Username has been taken";
		}
		userService.addUser(user);
		return "Saved";
	}
	
	/**
	 * Get a single user through id
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/users/{uid}")
	public @ResponseBody Optional<User> getUserById(@PathVariable String uid) {
		return userService.getUserByUid(uid);
	}
	
	/**
	 * Update the information of users
	 * @param id
	 * @param user
	 * @return
	 */
	@PutMapping(path = "/users/{uid}")
	public @ResponseBody String updateUser(@PathVariable String uid, @RequestBody User user) {
		User previousUser = userService.getUserByUid(uid).get();
		// check if modified username is exist
		if(userService.isExist(user.getUsername()) && !previousUser.getUsername().equals(user.getUsername())) {
			return "Username has been taken";
		}
		userService.updateUser(uid, user);
		return "Updated";
	}
	
	/**
	 * Delete the account of users
	 * @param id
	 * @return
	 */
	@DeleteMapping(path = "/users/{uid}")
	public @ResponseBody String deleteUser(@PathVariable String uid) {
		userService.deleteUserByUid(uid);
		return "Deleted";
	}
	
	/**
	 * Login
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/users/login")
	public User login(@RequestBody User user) { 
		return userService.login(user);
	}
}

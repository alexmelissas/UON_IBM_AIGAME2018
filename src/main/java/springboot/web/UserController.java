package springboot.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import springboot.domain.User;
import springboot.service.UserService;

@Controller
@RequestMapping(path = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/add")
	public @ResponseBody String addUser(@RequestParam String username, @RequestParam String password) {
		userService.addUser(username, password);
		return "Saved";
	}
	
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping(path = "/find")
	public @ResponseBody Optional<User> getUserById(@RequestParam int id) {
		return userService.getUserById(id);
	}
	
	@GetMapping(path = "/update")
	public @ResponseBody String updateUser(@RequestParam int id, @RequestParam String username, @RequestParam String password) {
		userService.updateUser(id, username, password);
		return "Updated";
	}
	
	@GetMapping(path = "/delete")
	public @ResponseBody String deleteUser(@RequestParam int id) {
		userService.deleteUserById(id);
		return "Deleted";
	}
}

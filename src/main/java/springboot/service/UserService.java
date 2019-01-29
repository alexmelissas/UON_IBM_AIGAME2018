package springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.domain.User;
import springboot.domain.UserRepository;

@Service("userService")
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void addUser(String username, String password) {
		User user = new User(username, password);
		
		userRepository.save(user);
	}
	
	public Optional<User> getUserById(int id) {
		return userRepository.findById(id);
	}
	
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public void updateUser(int id, String username, String password) {
		User user = new User(id, username, password);
		
		userRepository.save(user);
	}
	
	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}
}

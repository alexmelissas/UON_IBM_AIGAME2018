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
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public Optional<User> getUserByUid(String uid) {
		return userRepository.findByUid(uid);
	}
	
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public void updateUser(String uid, String username, String password) {
		User user = new User(uid, username, password);
		
		userRepository.save(user);
	}
	
	public void deleteUserById(int uid) {
		userRepository.deleteById(uid);
	}

	public void updateUser(String uid, User newUser) {
		User temp = userRepository.findByUid(uid).get();
		System.out.println(temp);
		System.out.println(newUser);
		userRepository.findByUid(uid)
						.map(user -> {
							user.setUsername(newUser.getUsername());
							user.setPassword(newUser.getPassword());
							
							// Use to store the authorization information after create account
							// Modification of username/password do not need to contains token info 
							if(newUser.getAccessToken() != null && newUser.getAccessTokenSecret() != null) {
								user.setAccessToken(newUser.getAccessToken());
								user.setAccessTokenSecret(newUser.getAccessTokenSecret());
							}
							return userRepository.save(user);
						});
	}

	public boolean isExist(String username) {
		return userRepository.existsByUsername(username);
	}
}

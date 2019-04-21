package springboot.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.domain.User;
import springboot.repository.UserRepository;
import springboot.service.IdealService;
import springboot.service.PlayerService;
import springboot.service.UserService;

/**
 * <p>
 * Implementation of {@link UserService}
 * </p>
 * 
 * @author chenyu
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IdealService idealService;
	@Autowired
	private PlayerService playerService;
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.UserService#addUser(springboot.domain.User)
	 */
	@Override
	public void addUser(User user) {
		userRepository.save(user);
		logger.info(">>>Create new user [id:{}, username:{}]", user.getId(), user.getUsername());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.UserService#getUserById(java.lang.String)
	 */
	@Override
	public User getUserById(String id) {
		User user = null;
		Optional<User> refUser = userRepository.findById(id);
		if (refUser.isPresent()) {
			user = refUser.get();
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.UserService#getAllUsers()
	 */
	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.UserService#deleteUserById(java.lang.String)
	 */
	@Override
	public void deleteUserById(String id) {
		userRepository.deleteById(id);
		if (idealService.isExist(id)) {
			idealService.deleteIdealById(id);
		}
		if (playerService.isExist(id)) {
			playerService.deletePlayerById(id);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.UserService#updateUser(java.lang.String,
	 * springboot.domain.User)
	 */
	@Override
	public void updateUser(String id, User newUser) {
		userRepository.findById(id).map(user -> {
			user.setUsername(newUser.getUsername());
			user.setPassword(newUser.getPassword());

			// Use to store the authorization information after create account
			// Modification of username/password do not need to contains token info
			if (newUser.getAccessToken() != null && newUser.getAccessTokenSecret() != null) {
				user.setAccessToken(newUser.getAccessToken());
				user.setAccessTokenSecret(newUser.getAccessTokenSecret());
			}
			logger.info(">>>Update user information [id:{}, username:{}]", user.getId(), user.getUsername());
			return userRepository.save(user);
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.UserService#deleteToken(java.lang.String)
	 */
	@Override
	public void deleteToken(String id) {
		userRepository.findById(id).map(user -> {
			user.setAccessToken(null);
			user.setAccessTokenSecret(null);
			logger.info(">>>Delete the token");

			return userRepository.save(user);
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.UserService#isExist(java.lang.String)
	 */
	@Override
	public boolean isExist(String username) {
		return userRepository.existsByUsername(username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.UserService#isExistById(java.lang.String)
	 */
	@Override
	public boolean isExistById(String id) {
		return userRepository.existsById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.UserService#login(springboot.domain.User)
	 */
	@Override
	public User login(User loginUser) {
		Optional<User> refUser = userRepository.findByUsername(loginUser.getUsername());
		User user = null;
		if (!refUser.isPresent()) {
		} else {
			user = refUser.get();
			if (!user.getPassword().equals(loginUser.getPassword())) {
				user = null;
			}
		}

		if (user != null) {
			logger.info(">>>User has logged in [id:{}, username:{}]", user.getId(), user.getUsername());
		} else {
			logger.info(">>>User login failed");
		}
		return user;
	}
}

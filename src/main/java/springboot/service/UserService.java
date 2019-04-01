package springboot.service;

import springboot.domain.User;

public interface UserService {
	public void addUser(User user);

	public User getUserById(String id);

	public Iterable<User> getAllUsers();

	public void deleteUserById(String id);

	public void updateUser(String id, User newUser);

	public boolean isExist(String username);

	public User login(User loginUser);

	boolean isExistById(String id);
}

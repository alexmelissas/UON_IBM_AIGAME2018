package springboot.service;

import springboot.domain.User;

/**
 * <p>
 * The UserService interface defines the operations on User in the database
 * </p>
 * 
 * @author chenyu
 *
 */
public interface UserService {
	/**
	 * Add a new user
	 * 
	 * @param user the new user
	 */
	public void addUser(User user);

	/**
	 * Get the user according to the id
	 * 
	 * @param id the id
	 * @return the user
	 */
	public User getUserById(String id);

	/**
	 * Get all the users
	 * 
	 * @return a list of users
	 */
	public Iterable<User> getAllUsers();

	/**
	 * Delete a user
	 * 
	 * @param id the id
	 */
	public void deleteUserById(String id);

	/**
	 * Update the user
	 * 
	 * @param id      the id
	 * @param newUser the updated user
	 */
	public void updateUser(String id, User newUser);

	/**
	 * Check if a username is available
	 * 
	 * @param username the user
	 * @return true for exist; false for not
	 */
	public boolean isExist(String username);

	/**
	 * Login
	 * 
	 * @param loginUser the user
	 * @return the user contains all the information
	 */
	public User login(User loginUser);

	/**
	 * Check if a user is already in database
	 * 
	 * @param id the id
	 * @return true for exist
	 */
	public boolean isExistById(String id);

	/**
	 * Delete the token of a user
	 * 
	 * @param id the id
	 */
	public void deleteToken(String id);
}

package springboot.service;

import springboot.domain.Ideal;

/**
 * <p>
 * The IdealService interface defines the operations on Ideal in the database
 * </p>
 * 
 * @author chenyu
 *
 */
public interface IdealService {
	/**
	 * Add a new ideal to database
	 * 
	 * @param ideal the ideal
	 */
	public void addIdeal(Ideal ideal);

	/**
	 * Get all the ideals from database
	 * 
	 * @return all the ideals
	 */
	public Iterable<Ideal> getIdeals();

	/**
	 * Get the ideal according to the id
	 * 
	 * @param id the id
	 * @return the ideal with the input id
	 */
	public Ideal getIdealById(String id);

	/**
	 * Initialize the ideal
	 * 
	 * @param id       the id
	 * @param newIdeal the initialized ideal
	 */
	public void initialIdeal(String id, Ideal newIdeal);

	/**
	 * Delete the ideal
	 * 
	 * @param id the id
	 */
	public void deleteIdealById(String id);

	/**
	 * Initialize the player
	 * 
	 * @param id    the id
	 * @param ideal the ideal
	 */
	public void initialPlayer(String id, Ideal ideal);

	/**
	 * Check if a ideal is already in database
	 * 
	 * @param id the id
	 * @return true for exist; false for not
	 */
	public boolean isExist(String id);

	/**
	 * Mark the ideal as unauthorized
	 * 
	 * @param id the id
	 */
	public void unAuth(String id);

	/**
	 * Mark the ideal as authorized
	 * 
	 * @param id the id
	 */
	public void reAuth(String id);
}

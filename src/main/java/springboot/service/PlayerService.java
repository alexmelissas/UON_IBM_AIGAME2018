package springboot.service;

import java.util.List;
import springboot.domain.Player;

/**
 * <p>
 * The PlayerService interface defines the operations on Player in the database
 * </p>
 * 
 * @author chenyu
 *
 */
public interface PlayerService {
	/**
	 * Add a new player
	 * 
	 * @param player the new player
	 */
	public void addPlayer(Player player);

	/**
	 * Get all the players
	 * 
	 * @return
	 */
	public List<Player> getPlayers();
	
	/**
	 * Get all the players by group
	 * 
	 * @return
	 */
	public List<Player> getPlayersByGroup(int group);

	/**
	 * Get the player according to id
	 * 
	 * @param id the id
	 * @return the player
	 */
	public Player getPlayerById(String id);

	/**
	 * Get the list of top 10 players
	 * 
	 * @return a list of players
	 */
	public List<Player> getTopPlayers();

	/**
	 * Get the rank of player
	 * 
	 * @param id the id
	 * @return the rank of player
	 */
	public int getRankById(String id);

	/**
	 * Get the players with specific level
	 * 
	 * @param level the level
	 * @return a list of players with the input level
	 */
	public List<Player> getPlayersByLevel(int level);

	/**
	 * Update the status of player
	 * 
	 * @param id        the id
	 * @param newPlayer the updated player
	 */
	public void updatePlayer(String id, Player newPlayer);

	/**
	 * Delete the player
	 * 
	 * @param id the id
	 */
	public void deletePlayerById(String id);

	/**
	 * Check if a player is already in database
	 * 
	 * @param id the id
	 * @return true for exist
	 */
	boolean isExist(String id);
}

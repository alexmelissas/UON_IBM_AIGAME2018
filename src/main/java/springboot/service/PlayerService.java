package springboot.service;

import java.util.List;
import springboot.domain.Player;

public interface PlayerService {
	public void addPlayer(Player player);

	public Iterable<Player> getPlayers();

	public Player getPlayerById(String id);

	public List<Player> getTopPlayers();

	public int getRankById(String id);

	public List<Player> getPlayersByLevel(int level);

	public void updatePlayer(String id, Player newPlayer);

	public void deletePlayerById(String id);

	boolean isExist(String id);
}

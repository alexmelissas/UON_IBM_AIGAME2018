package springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import springboot.domain.Player;
import springboot.domain.PlayerRepository;

@Service("playerService")
public class PlayerService {
	@Autowired
	private PlayerRepository playerRepository;

	public void addPlayer(Player player) {
		playerRepository.save(player);
	}

	public Iterable<Player> getPlayers() {
		return playerRepository.findAll();
	}

	public Player getPlayerById(String id) {
		Player player = null;
		Optional<Player> refPlayer = playerRepository.findById(id);
		if (refPlayer.isPresent()) {
			player = refPlayer.get();
		} else {
		}
		return player;
	}

	// TODO think about the rank
	public List<Player> getTopPlayers() {
		Sort sort = new Sort(Direction.DESC, "score");
		List<Player> players = playerRepository.findAll(sort);
		ArrayList<Player> topPlayers = new ArrayList<Player>();
		int count = 0;
		for (Player p : players) {
			if (count >= 5) {
				break;
			}
			topPlayers.add(p);
			count++;
		}
		return topPlayers;
	}

	public int getRankById(String id) {
		Sort sort = new Sort(Direction.DESC, "score");
		List<Player> players = playerRepository.findAll(sort);
		int count = 0;
		for (Player p : players) {
			if (p.getId().equals(id)) {
				break;
			}
			count++;
		}
		return count;
	}

	public List<Player> getPlayersByLevel(int level) {
		return playerRepository.findAllByLevel(level);
	}

	// update the neitre 
	public void updatePlayer(String id, Player newPlayer) {
		System.out.println("Try to update player:");
		System.out.println("new: " + newPlayer);
		
		// TODO can update the entire player status
		// BUG battle can update but request cannot
		// need to sepcific => items/status (including battle information)
		playerRepository.findById(id).map(player -> {
//			player.setHp(newPlayer.getHp());
//			player.setAttack(newPlayer.getAttack());
//			player.setDefense(newPlayer.getDefense());
//			player.setAgility(newPlayer.getAgility());
//			player.setCriticalStrike(newPlayer.getCriticalStrike());
//			player.setShield(newPlayer.getShield());
//			player.setArmour(newPlayer.getArmour());
//			player.setSword(newPlayer.getSword());
			// TODO check the update of player
			return playerRepository.save(player);
		});
	}

	// TODO update items level
	public void updateItems(String id, Player newPlayer) {

	}
	

	// TODO update factor

	public void deletePlayerById(String id) {
		playerRepository.deleteById(id);

	}
}

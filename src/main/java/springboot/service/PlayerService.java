package springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import springboot.domain.Player;
import springboot.domain.PlayerRepository;

@Service("playerService")
public class PlayerService {
	@Autowired
	private PlayerRepository playerRepository;
	private static Logger logger = LoggerFactory.getLogger(PlayerService.class);

	public void addPlayer(Player player) {
		playerRepository.save(player);
		logger.info(">>>Create new player [player:{}]", player);
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

	// update the player
	public void updatePlayer(String id, Player newPlayer) {
		logger.info(">>>Update the player");
		playerRepository.findById(id).map(player -> {
			// update the basic status
			if (newPlayer.getHp() != 0 && newPlayer.getAttack() != 0 && newPlayer.getDefense() != 0
					&& newPlayer.getAgility() != 0 && newPlayer.getCriticalStrike() != 0) {
				player.setHp(newPlayer.getHp());
				player.setAttack(newPlayer.getAttack());
				player.setDefense(newPlayer.getDefense());
				player.setAgility(newPlayer.getAgility());
				player.setCriticalStrike(newPlayer.getCriticalStrike());
				logger.info(">>>Update statuses of [player:{}]", player);
			}

			// update the items level
			if (newPlayer.getArmour() > player.getArmour() || newPlayer.getShield() > player.getSword()
					|| newPlayer.getSword() > player.getSword()) {
				player.setArmour(newPlayer.getArmour());
				player.setShield(newPlayer.getShield());
				player.setSword(newPlayer.getSword());
				logger.info(">>>Update items of [player:{}]", player);
			}

			return playerRepository.save(player);
		});
	}

	// TODO update factor
	public void update(String id, Player newPlayer) {

	}

	public void deletePlayerById(String id) {
		playerRepository.deleteById(id);

	}
}

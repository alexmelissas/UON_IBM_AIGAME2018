package springboot.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.domain.Player;
import springboot.repository.PlayerRepository;
import springboot.service.PlayerService;

/**
 * <p>
 * Implementation of {@link PlayerService}
 * </p>
 * 
 * @author chenyu
 *
 */
@Service("playerService")
public class PlayerServiceImpl implements PlayerService {
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private RedisService redisService;
	private static Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.PlayerService#addPlayer(springboot.domain.Player)
	 */
	@Override
	public void addPlayer(Player player) {
		playerRepository.save(player);
		logger.info(">>>Create new player [player:{}]", player);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.PlayerService#getPlayers()
	 */
	@Override
	public List<Player> getPlayers() {
		List<Player> players = playerRepository.findAll();
		for (Player player : players) {
			player.setRankScore(redisService.getRankedScore(player.getId()));
		}
		return players;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.PlayerService#getPlayerById(java.lang.String)
	 */
	@Override
	public Player getPlayerById(String id) {
		Player player = null;
		Optional<Player> refPlayer = playerRepository.findById(id);
		if (refPlayer.isPresent()) {
			player = refPlayer.get();
			player.setRankScore(redisService.getRankedScore(player.getId()));
		} else {
		}
		return player;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.PlayerService#getRankById(java.lang.String)
	 */
	@Override
	public int getRankById(String id) {
		List<Player> players = this.getPlayers();
		Collections.sort(players, new Comparator<Player>() {

			@Override
			public int compare(Player p1, Player p2) {
				if (p1.getRankScore() < p2.getRankScore()) {
					return 1;
				} else if (p1.getRankScore() == p2.getRankScore()) {
					return 0;
				} else {
					return -1;
				}
			}
		});
		
		int count = 0;
		for (Player p : players) {
			if (p.getId().equals(id)) {
				break;
			}
			count++;
		}
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.PlayerService#getPlayersByLevel(int)
	 */
	@Override
	public List<Player> getPlayersByLevel(int level) {
		List<Player> players = playerRepository.findAllByLevel(level);
		for (Player player : players) {
			player.setRankScore(redisService.getRankedScore(player.getId()));
		}
		return players;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.PlayerService#updatePlayer(java.lang.String,
	 * springboot.domain.Player)
	 */
	@Override
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
				logger.info(">>>Update the statuses of [player:{}]", player);
			}

			// update the items level
			if (newPlayer.getArmour() > player.getArmour() || newPlayer.getShield() > player.getSword()
					|| newPlayer.getSword() > player.getSword()) {
				player.setArmour(newPlayer.getArmour());
				player.setShield(newPlayer.getShield());
				player.setSword(newPlayer.getSword());
				player.setMoney(newPlayer.getMoney());
				logger.info(">>>Update the items of [player:{}]", player);
			}

			if (newPlayer.getFactor() != player.getFactor()) {
				player.setFactor(newPlayer.getFactor());
				logger.info(">>>Update the factor of [player:{}]", player);
			}

			return playerRepository.save(player);
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.PlayerService#deletePlayerById(java.lang.String)
	 */
	@Override
	public void deletePlayerById(String id) {
		playerRepository.deleteById(id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.PlayerService#isExist(java.lang.String)
	 */
	@Override
	public boolean isExist(String id) {
		return playerRepository.existsById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.PlayerService#getPlayersByGroup(int)
	 */
	@Override
	public List<Player> getPlayersByGroup(int group) {
		List<Player> players = playerRepository.findAllByGroup(group);
		for (Player player : players) {
			player.setRankScore(redisService.getRankedScore(player.getId()));
		}
		return players;
	}
}

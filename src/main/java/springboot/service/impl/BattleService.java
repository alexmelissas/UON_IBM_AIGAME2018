package springboot.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.config.PlayerConfig;
import springboot.domain.Bot;
import springboot.domain.Player;
import springboot.service.PlayerService;

/**
 * <p>
 * The BattleService class is used to handle the result of battle
 * </p>
 * 
 * @author chenyu
 *
 */
@Service("battleService")
public class BattleService {
	@Autowired
	private PlayerService playerService;
	private Random random = new Random();
	private static Logger logger = LoggerFactory.getLogger(BattleService.class);

	/**
	 * Handle the result of battle
	 * 
	 * @param id1             the current player
	 * @param id2             the opponent player
	 * @param result          true for win; false for lose
	 * @param additionalExp   the additional experience
	 * @param additionalMoney the additional money
	 * @return the updated player
	 */
	public Player handleResult(String id1, String id2, boolean result, int additionalExp, int additionalMoney) {
		logger.info(">>>Player {} {}s {}", id1, (result ? "win" : "lose"), id2);
		logger.info(">>>Additional reward [experience:{}, money:{}]", additionalExp, additionalMoney);

		Player player1 = playerService.getPlayerById(id1);
		int exp = additionalExp;
		int money = additionalMoney;
		boolean isBot = "bot".equals(id2);

		if (result) {
			// player win
			exp += isBot ? 10 : 20;
			money += isBot ? 20 : 50;
			player1.setWin(player1.getWin() + (isBot ? 0 : 1));
		} else {
			// player lose
			exp += isBot ? 3 : 6;
			money += isBot ? 6 : 15;
			player1.setLose(player1.getLose() + (isBot ? 0 : 1));
		}
		player1.setExperience(exp + player1.getExperience());
		player1.setMoney(money + player1.getMoney());

		player1.checklevelUp();
		playerService.updatePlayer(id1, player1);

		return player1;
	}

	/**
	 * Get a random player
	 * 
	 * @param id the id of current player
	 * @return a random player with similar level
	 */
	public Player getRandomPlayer(String id) {
		int level = playerService.getPlayerById(id).getLevel();
		List<Player> players = playerService.getPlayersByLevel(level - 1);
		players.addAll(playerService.getPlayersByLevel(level));
		players.addAll(playerService.getPlayersByLevel(level + 1));

		Set<Player> playerSet = new HashSet<>(players);
		List<Player> playerList = new ArrayList<Player>(playerSet);

		int randomIndex = random.nextInt(playerSet.size());
		Player player = playerList.get(randomIndex);

		if (players.size() == 1) {
			Random random = new Random();
			if (random.nextDouble() < (2.0 / 3.0))  {
				player.setFactor(player.getFactor() - random.nextDouble() / 6);
			} else {
				player.setFactor(player.getFactor() + random.nextDouble() / 6);
			}
			player.setAttributes(PlayerConfig.getBasicStatus(player.getLevel()));
			player.applyPersonality();
			return player;
		}
		
		while (id.equals(player.getId())) {
			randomIndex = random.nextInt(playerSet.size());
			player = playerList.get(randomIndex);
		}

		return player;
	}

	/**
	 * Get a bot according to the difficulty and player
	 * 
	 * @param id         the id of current player
	 * @param difficulty the difficulty
	 * @return a bot player
	 */
	public Bot getBot(String id, String difficulty) {
		int level = playerService.getPlayerById(id).getLevel();

		// Generate level for bot randomly according to difficult
		double randomDouble = random.nextDouble();
		if ("easy".equals(difficulty)) {
			if (randomDouble < 0.666666) {
				level = level > 1 ? level-- : level;
			} else {
			}
		} else if ("medium".equals(difficulty)) {
			System.out.println("medium");
			if (randomDouble < 0.233333) {
				level = level > 1 ? level-- : level;
			} else if (randomDouble > 0.766666) {
				level++;
			} else {
			}
		} else {
			System.out.println("hard");
			if (randomDouble < 0.233333) {
			} else {
				level++;
			}
		}

		// Generate factor for bot according to difficult
		double randomFactor = 0;
		double randomInt = random.nextInt(4);
		if ("easy".equals(difficulty)) {
			// 0.9 -1.2
			randomFactor = randomInt / 10 + 0.9;
		} else if ("medium".equals(difficulty)) {
			// 1.2 - 1.5
			randomFactor = randomInt / 10 + 1.2;
		} else {
			// 1.5 - 1.8
			randomFactor = randomInt / 10 + 1.5;
		}

		Bot bot = new Bot(level, randomFactor);
		bot.applyPersonality();
		return bot;
	}

	/**
	 * Check if a player is in database
	 * 
	 * @param id the id of current player
	 * @return true for exist
	 */
	public boolean isExist(String id) {
		return playerService.isExist(id);
	}
}

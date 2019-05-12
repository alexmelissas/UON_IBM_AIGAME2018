package springboot.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
	@Autowired
	private RedisService redisService;
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
			if (random.nextDouble() < (2.0 / 3.0)) {
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

		redisService.addBattleCount(id, "");
		logger.info(">>>Left number of battles: {}", 10 - redisService.getBattleCount(id, ""));
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

	/**
	 * Get 5 random player with the same level as the current player
	 * 
	 * @param id the id
	 * @return the list of 5 players
	 */
	public List<Player> getRankedPlayer(String id) {
		Player player = playerService.getPlayerById(id);

		int level = playerService.getPlayerById(id).getLevel();
		List<Player> players = playerService.getPlayersByLevel(level);

		Random random = new Random();
		while (players.size() <= 5) {
			Player fakePlayer = new Player();
			if (random.nextDouble() < (2.0 / 3.0)) {
				fakePlayer.setFactor(player.getFactor() - random.nextDouble() / 6);
			} else {
				fakePlayer.setFactor(player.getFactor() + random.nextDouble() / 6);
			}
			fakePlayer.setAttributes(PlayerConfig.getBasicStatus(player.getLevel()));
			fakePlayer.applyPersonality();
			fakePlayer.setId("fake");
			// TODO random name
			players.add(fakePlayer);
		}

		List<Player> playerList = new ArrayList<Player>(players);
		List<Player> randomPlayers = new ArrayList<Player>();
		int randomIndex = random.nextInt(playerList.size());
		int randomGroup = random.nextInt(4);

		while (randomPlayers.size() < 5) {
			Player temp = playerList.get(randomIndex);
			while (id.equals(temp.getId()) || randomPlayers.contains(temp) || temp.getGroup() == player.getGroup()) {
				randomIndex = random.nextInt(playerList.size());
				temp = playerList.get(randomIndex);
			}

			if (temp.getGroup() == -1) {
				while (randomGroup == 0 || randomGroup == player.getGroup()) {
					randomGroup = random.nextInt(4);
				}
				temp.setGroup(randomGroup);
			}
			randomPlayers.add(temp);
			randomGroup = random.nextInt(4);
		}

		return randomPlayers;
	}

	/**
	 * Get the number of battles of a player
	 * 
	 * @param id  the id
	 * @param str indicate the battle is ranked or unranked
	 * @return the number of battles
	 */
	public int getBattleCount(String id, String str) {
		return redisService.getBattleCount(id, str);
	}

	/**
	 * Get the score of a player
	 * 
	 * @param id the id
	 * @return the score
	 */
	public int getRankedScore(String id) {
		return redisService.getRankedScore(id);
	}

	/**
	 * Handle the ranked battle
	 * 
	 * @param id1             the current player
	 * @param id2             the enemy
	 * @param result          result of battle
	 * @param additionalExp   additional experience
	 * @param additionalMoney additional money
	 */
	public synchronized Player handleRankedResult(String id1, String id2, boolean result, int additionalExp,
			int additionalMoney) {
		// update individual score
		int score = redisService.getRankedScore(id1);
		score += result ? 15 : 3;
		redisService.setRankedScore(id1, score);
		redisService.addBattleCount(id1, "ranked_");
		// TODO winning streak bonus

		// update group score
		int groupNum = playerService.getPlayerById(id1).getGroup();
		int groupScore = redisService.getGroupScore(groupNum);
		groupScore += result ? 15 : 3;
		redisService.setGroupScore(groupNum, groupScore);

		Player player = playerService.getPlayerById(id1);
		return player;
	}

	/**
	 * Get the rank of each group
	 * 
	 * @return the rank of groups and their scores
	 */
	public HashMap<Integer, Integer> getGroupRank() {
		HashMap<Integer, Integer> scoreBoard = new HashMap<Integer, Integer>();
		for (int i = 1; i <= 3; i++) {
			scoreBoard.put(i, redisService.getGroupScore(i));
		}
		return scoreBoard;
	}

	/**
	 * Get the top ten player in the group
	 * 
	 * @param groupNum the group number
	 * @return the rank of players in the group
	 */
	public HashMap<String, Integer> getGroupPlayerRank(String groupNum) {
		List<Player> players = playerService.getPlayersByGroup(Integer.parseInt(groupNum));
		HashMap<String, Integer> playerBoard = new HashMap<String, Integer>();
		for (Player player : players) {
			int score = redisService.getRankedScore(player.getId());
			playerBoard.put(player.getCharacterName(), score);
		}
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(playerBoard.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		int count = 0;
		for (Map.Entry<String, Integer> mapping : list) {
			if (count >= 10)
				break;
			result.put(mapping.getKey(), mapping.getValue());
			count++;
		}
		return result;
	}
}

package springboot.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import springboot.domain.Bot;
import springboot.domain.Player;
import springboot.service.impl.BattleService;

/**
 * <p>
 * The BattleController class is used to handle the battle related requests
 * </p>
 * 
 * @author chenyu
 *
 */
@RestController
public class BattleController {
	@Autowired
	private BattleService battleService;
	private static Logger logger = LoggerFactory.getLogger(BattleController.class);

	/**
	 * Get a random player for current player
	 * 
	 * @param id the id of current player
	 * @return the random player
	 */
	@GetMapping("/battle/{id}")
	public Player getRandomPlayer(@PathVariable String id) {
		if (!battleService.isExist(id)) {
			return null;
		}
		return battleService.getRandomPlayer(id);
	}

	/**
	 * Get a bot player for current player
	 * 
	 * @param difficulty the difficulty
	 * @param id         the id
	 * @return the generated bot player
	 */
	@GetMapping("/battle/{difficulty}/{id}")
	public Bot getBot(@PathVariable String difficulty, @PathVariable String id) {
		if (!battleService.isExist(id)) {
			return null;
		}
		return battleService.getBot(id, difficulty);
	}

	/**
	 * Get the number of battles of a player
	 * 
	 * @param id the id
	 * @return the number of battles
	 */
	@GetMapping("/battle/count/{id}")
	public @ResponseBody int getBattleCount(@PathVariable String id) {
		if (!battleService.isExist(id)) {
			return -1;
		}
		return battleService.getBattleCount(id, "");
	}

	/**
	 * Get the number of ranked battles of a player
	 * 
	 * @param id the id
	 * @return the number of battles
	 */
	@GetMapping("/battle/ranked/count/{id}")
	public @ResponseBody int getRankedBattleCount(@PathVariable String id) {
		if (!battleService.isExist(id)) {
			return -1;
		}
		return battleService.getBattleCount(id, "ranked_");
	}

	/**
	 * Get the rank score of a player
	 * 
	 * @param id the id
	 * @return the score
	 */
	@GetMapping("/battle/ranked/score/{id}")
	public @ResponseBody int getRankedScore(@PathVariable String id) {
		if (!battleService.isExist(id)) {
			return -1;
		}
		return battleService.getRankedScore(id);
	}

	/**
	 * Get 5 random players
	 * 
	 * @param id the id of current player
	 * @return the list of players
	 */
	@GetMapping("/battle/ranked/{id}")
	public @ResponseBody List<Player> getRankedPlayers(@PathVariable String id) {
		if (!battleService.isExist(id)) {
			return null;
		}
		return battleService.getRankedPlayer(id);
	}

	/**
	 * Get the rank of each group
	 * 
	 * @return the rank of groups and their scores
	 */
	@GetMapping("/battle/ranked/group")
	public @ResponseBody HashMap<Integer, Integer> getGroupRank() {
		return battleService.getGroupRank();
	}

	/**
	 * Get the top ten player in the group
	 * 
	 * @param groupNum the group number
	 * @return the rank of players in the group
	 */
	@GetMapping("/battle/ranked/group/{groupNum}")
	public @ResponseBody HashMap<String, Integer> getGroupPlayerRank(@PathVariable String groupNum) {
		return battleService.getGroupPlayerRank(groupNum);
	}

	/**
	 * Handle the result of battle
	 * 
	 * @param jsonString json string which contains the information of battle
	 * @return the updated player
	 */
	@PutMapping("/battle")
	public Player handleResult(@RequestBody String jsonString) {
		logger.info("======Battle Result======");
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(jsonString);
		String id1 = jsonObject.get("id1").getAsString();
		String id2 = jsonObject.get("id2").getAsString();
		// true: id1 win; false: id1 lost;
		boolean result = jsonObject.get("result").getAsBoolean();
		int additionalExp = jsonObject.get("additionalExp").getAsInt();
		int additionalMoney = jsonObject.get("additionalMoney").getAsInt();

		Player player = battleService.handleResult(id1, id2, result, additionalExp, additionalMoney);
		logger.info("======Battle Result End======");
		return player;
	}

	/**
	 * Handle the ranked battle
	 * 
	 * @param jsonString the json result
	 */
	@PutMapping("/battle/ranked")
	public Player handleRankedResult(@RequestBody String jsonString) {
		logger.info("======Ranked Battle Result======");
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(jsonString);
		String id1 = jsonObject.get("id1").getAsString();
		String id2 = jsonObject.get("id2").getAsString();
		// true: id1 win; false: id1 lost;
		boolean result = jsonObject.get("result").getAsBoolean();
		int additionalExp = jsonObject.get("additionalExp").getAsInt();
		int additionalMoney = jsonObject.get("additionalMoney").getAsInt();

		Player player = battleService.handleRankedResult(id1, id2, result, additionalExp, additionalMoney);

		logger.info("======Ranked Battle Result End======");
		return player;
	}
}
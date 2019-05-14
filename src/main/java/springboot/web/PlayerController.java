package springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.domain.Player;
import springboot.service.PlayerService;

/**
 * <p>
 * The PlayerController class is used to handle the player related requests.
 * </p>
 * 
 * @author chenyu
 *
 */
@RestController
public class PlayerController {
	@Autowired
	private PlayerService playerService;
	private static Logger logger = LoggerFactory.getLogger(PlayerController.class);

	/**
	 * Get all the players
	 * 
	 * @return a list of all the players
	 */
	@GetMapping("/players")
	public Iterable<Player> getPlayers() {
		return playerService.getPlayers();
	}

	/**
	 * Update the player
	 * 
	 * @param id     the id
	 * @param player the updated player
	 * @return the result
	 */
	@PutMapping("/players/{id}")
	public String updatePlayer(@PathVariable String id, @RequestBody Player player) {
		if (!playerService.isExist(id)) {
			return "Failed";
		}
		logger.info("======Update Player======");
		playerService.updatePlayer(id, player);
		logger.info("======Update Player End======");
		return "Updated";
	}

	/**
	 * Get the player with input id
	 * 
	 * @param id the id
	 * @return the player with the id
	 */
	@GetMapping("/players/{id}")
	public @ResponseBody Player getPlayerById(@PathVariable String id) {
		Player player = playerService.getPlayerById(id);
		return player;
	}

	/**
	 * Get the rank of a player
	 * 
	 * @param id the id
	 * @return the rank
	 */
	@GetMapping("/players/rank/{id}")
	public int getPlayerRankById(@PathVariable String id) {
		return playerService.getRankById(id);
	}
}

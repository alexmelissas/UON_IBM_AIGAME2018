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

@RestController
public class PlayerController {
	@Autowired
	private PlayerService playerService;
	private static Logger logger = LoggerFactory.getLogger(PlayerController.class);

	@GetMapping("/players")
	public Iterable<Player> getPlayers() {
		return playerService.getPlayers();
	}

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

	@GetMapping("/players/{id}")
	public @ResponseBody Player getPlayerById(@PathVariable String id) {
		Player player = playerService.getPlayerById(id);
		return player;
	}

	// TODO keep?
	@GetMapping("/players/rank/{id}")
	public int getPlayerRankById(@PathVariable String id) {
		return playerService.getRankById(id);
	}
}

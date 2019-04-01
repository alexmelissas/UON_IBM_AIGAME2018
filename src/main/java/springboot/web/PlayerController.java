package springboot.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		logger.info("======Update User======");
		playerService.updatePlayer(id, player);
		logger.info("======Update User End======");
		return "Updated";
	}

	@GetMapping("/players/{id}")
	public @ResponseBody Player getPlayerById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String id) {
		if (!playerService.isExist(id)) {
			try {
				request.getRequestDispatcher("/404").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		Player player = playerService.getPlayerById(id);
		return player;
	}

	@GetMapping("/players/rank/{id}")
	public int getPlayerRankById(@PathVariable String id) {
		return playerService.getRankById(id);
	}
}

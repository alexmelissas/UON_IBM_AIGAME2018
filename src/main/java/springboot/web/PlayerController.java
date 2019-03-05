package springboot.web;

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
	
	@GetMapping("/players")
	public Iterable<Player> getPlayers() {
		return playerService.getPlayers();
	}
	
	@PutMapping("/players/{id}")
	public void updatePlayer(@PathVariable String id, @RequestBody Player player) {
		
	}
	
	@GetMapping("/players/{id}")
	public @ResponseBody Player getPlayerById(@PathVariable String id) {
		Player player = playerService.getPlayerById(id);
		return player;
	}
	
	@GetMapping("/players/rank/{id}")
	public int getPlayerRankById(@PathVariable String id) {
		return playerService.getRankById(id);
	}
	
	// TODO interval to check twitter 
	
}

package springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.domain.Ideal;
import springboot.domain.Player;
import springboot.service.IdealService;
import springboot.service.PlayerService;

@RestController
public class IdealController {
	@Autowired
	private IdealService idealService;
	@Autowired
	private PlayerService playerService;

	@GetMapping("/ideals")
	public @ResponseBody Iterable<Ideal> getIdeals() {
		return idealService.getIdeals();
	}

	@GetMapping("/ideas/{id}")
	public boolean isExistIdeal(@PathVariable String id) {
		return idealService.getIdealById(id) != null;
	}

	@PutMapping("/ideals/{id}")
	public @ResponseBody Player updateIdeal(@PathVariable String id, @RequestBody Ideal newIdeal) {
		// UPDATE IDEAL PERSONALITY

		// TODO exception handle
		newIdeal.setId(id);
		// UPDATE IDEAL AND PLAYER
		idealService.updateIdeal(id, newIdeal);
		System.out.println(newIdeal);
		return playerService.getPlayerById(id);
	}
}

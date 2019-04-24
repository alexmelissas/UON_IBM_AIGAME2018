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

import springboot.domain.Ideal;
import springboot.domain.Player;
import springboot.service.IdealService;
import springboot.service.PlayerService;

/**
 * <p>
 * The IdealController class is used to handle the ideal related requests.
 * </p>
 * 
 * @author chenyu
 *
 */
@RestController
public class IdealController {
	@Autowired
	private IdealService idealService;
	@Autowired
	private PlayerService playerService;
	private static Logger logger = LoggerFactory.getLogger(IdealController.class);

	/**
	 * Get all the ideals
	 * 
	 * @return a list of ideals
	 */
	@GetMapping("/ideals")
	public @ResponseBody Iterable<Ideal> getIdeals() {
		return idealService.getIdeals();
	}

	/**
	 * Check if a ideal exists
	 * 
	 * @param id the id of ideal
	 * @return true for exist
	 */
	@GetMapping("/ideals/{id}")
	public boolean isExistIdeal(@PathVariable String id) {
		return idealService.getIdealById(id) != null;
	}

	/**
	 * Initialize the ideal 
	 * @param id the id
	 * @param newIdeal the initialized ideal
	 * @return the newest player
	 */
	@PutMapping("/ideals/{id}")
	public @ResponseBody Player initialIdeal(@PathVariable String id, @RequestBody Ideal newIdeal) {
		if (!idealService.isExist(id)) {
			return null;
		}
		logger.info("======Initialize Ideal======");
		// UPDATE IDEAL PERSONALITY
		// THIS METHOD WILL BE CALLED ONLY ONCE

		newIdeal.setId(id);
		// UPDATE IDEAL AND PLAYER
		idealService.initialIdeal(id, newIdeal);
		logger.info("======Initialize Ideal End======");
		return playerService.getPlayerById(id);
	}
}

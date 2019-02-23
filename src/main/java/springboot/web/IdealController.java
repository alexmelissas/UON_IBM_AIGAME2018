package springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.domain.Ideal;
import springboot.service.IdealService;

@RestController
public class IdealController {
	@Autowired
	private IdealService idealService;

	@GetMapping("/ideals")
	public @ResponseBody Iterable<Ideal> getIdeals() {
		return idealService.getIdeals();
	}
	
	@PutMapping("/ideals/{id}")
	public @ResponseBody String updateIdeal(@PathVariable String id, @RequestBody Ideal ideal) {
		// UPDATE IDEAL PERSONALITY
		
		// TODO exception handle
		ideal.setId(id);
		idealService.updateIdeal(id, ideal);
		return "Updated";
	}
}

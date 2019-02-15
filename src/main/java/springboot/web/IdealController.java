package springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.domain.Ideal;
import springboot.service.IdealService;
import springboot.service.RoleService;

@RestController
public class IdealController {
	@Autowired
	private IdealService idealService;
	
	@PostMapping("/ideals/{id}")
	public @ResponseBody String addIdeal(@PathVariable String id, @RequestBody Ideal ideal) {
		// TODO add ideal
		// Create role for user
		ideal.setId(id);
		idealService.addIdeal(ideal);
		return "Saved";
	}
	
}

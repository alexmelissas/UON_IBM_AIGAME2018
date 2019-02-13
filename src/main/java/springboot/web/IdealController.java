package springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.service.IdealService;
import springboot.service.RoleService;

@RestController
public class IdealController {
	@Autowired
	private IdealService idealService;
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/ideals/{id}")
	public void addIdeal(@PathVariable String id) {
		// TODO add ideal
		// Create role for user
		
	}
}

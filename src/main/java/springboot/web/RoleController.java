package springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.domain.Role;
import springboot.service.RoleService;

@RestController
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/roles")
	public Iterable<Role> getTopRoles() {
		return roleService.getTopRoles();
	}
	
	@PutMapping("/roles/{id}")
	public void updateRole(@PathVariable String id, @RequestBody Role role) {
		
	}
	
	@GetMapping("roles/{id}")
	public int getRankById(@PathVariable String id) {
		return roleService.getRankById(id);
	}
}

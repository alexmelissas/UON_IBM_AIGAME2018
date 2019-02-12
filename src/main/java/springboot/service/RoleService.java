package springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.domain.Role;
import springboot.domain.RoleRepository;

@Service("roleService")
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public void addRole(Role role) {
		roleRepository.save(role);
	}
	
	public Iterable<Role> getRoles() {
		return roleRepository.findAll();
	}
	
	public Optional<Role> getRoleById(String id) {
		return roleRepository.findById(id); 
	}
	
	public void updateRole(String id, Role newRole) {
		roleRepository.findById(id)
						.map(role -> {
							role.setHp(newRole.getHp());
							role.setAttack(newRole.getAttack());
							role.setDefence(newRole.getDefence());
							role.setAgility(newRole.getAgility());
							role.setIntelligence(newRole.getIntelligence());
							
							if(!newRole.getJsonResult().equals("")) {
								role.setJsonResult(newRole.getJsonResult());
							}
							return roleRepository.save(role);
						});
	}
	
	public void deleteRoleById(String id) {
		roleRepository.deleteById(id);
		
	}
}

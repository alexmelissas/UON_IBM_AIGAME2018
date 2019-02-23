package springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	
	public List<Role> getTopRoles() {
		Sort sort = new Sort(Direction.DESC, "score");
		List<Role> roles = roleRepository.findAll(sort);
		ArrayList<Role> topRoles = new ArrayList<Role>(); 
		int count = 0;
		for(Role r : roles) {
			if(count >= 5) {
				break;
			}
			topRoles.add(r);
			count++;
		}
		return topRoles;
	}
	
	public int getRankById(String id) {
		Sort sort = new Sort(Direction.DESC, "score");
		List<Role> roles = roleRepository.findAll(sort);
		int count = 0;
		for(Role r : roles) {
			if(r.getId().equals(id)) {
				break;
			}
			count++;
		}
		return count;
	}
	
	public void updateRole(String id, Role newRole) {
		roleRepository.findById(id)
						.map(role -> {
							role.setHp(newRole.getHp());
							role.setAttack(newRole.getAttack());
							role.setDefence(newRole.getDefence());
							role.setAgility(newRole.getAgility());
							role.setIntelligence(newRole.getIntelligence());

							// TODO check the update of role
							// long way to go :(
							return roleRepository.save(role);
						});
	}
	
	public void deleteRoleById(String id) {
		roleRepository.deleteById(id);
		
	}
}

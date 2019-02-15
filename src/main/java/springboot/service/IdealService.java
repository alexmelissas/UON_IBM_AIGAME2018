package springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.domain.Ideal;
import springboot.domain.IdealRepository;
import springboot.domain.Role;
import springboot.util.AnalysisResult;

@Service("idealService")
public class IdealService {
	@Autowired
	private IdealRepository idealRepository;
	@Autowired
	private RoleService roleService;
	
	public void addIdeal(Ideal ideal) {
		idealRepository.save(ideal);
	}
	
	public Iterable<Ideal> getIdeals() {
		return idealRepository.findAll();
	}
	
	public Optional<Ideal> getIdealById(String id) {
		return idealRepository.findById(id);
	}
	
	// TODO If the user can change ideal personality?
	public void updateIdeal() {
		
	}
	
	public void deleteIdealById(String id) {
		idealRepository.deleteById(id);
	}
	
	public void generateRole(Ideal ideal) {
		AnalysisResult analysisResult;
    	String id = ideal.getId();
    	String jsonResult = roleService.getRoleById(id).get().getJsonResult();
 
    	analysisResult = new AnalysisResult(jsonResult);
    	Role newRole;
    	
    	if(jsonResult != null) {
    		newRole = analysisResult.generateRole(ideal);
    	} else {
    		// TODO generate the normal role
    		newRole = analysisResult.generateNormalRole();
    	}
    	
    	roleService.updateRole(id, newRole);
	}
}

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
	
	public void updateIdeal(String id, Ideal newIdeal) {
		// This method will only be called once when the user submit the ideal personality
		
		idealRepository.findById(id)
						.map(ideal -> {
							ideal.setAgreeableness(newIdeal.getAgreeableness());
							ideal.setConscientiousness(newIdeal.getConscientiousness());
							ideal.setEmotionalrange(newIdeal.getEmotionalrange());
							ideal.setExtraversion(newIdeal.getExtraversion());
							ideal.setOpeness(newIdeal.getOpeness());
							
							this.generateRole(ideal);
							return idealRepository.save(ideal);
						});
	}
	
	public void deleteIdealById(String id) {
		idealRepository.deleteById(id);
	}
	
	public void generateRole(Ideal ideal) {
		AnalysisResult analysisResult;
    	String id = ideal.getId();
    	String jsonResult = this.getIdealById(id).get().getJsonResult();
 
    	analysisResult = new AnalysisResult();
    	Role role;
    	
    	System.out.println("------" + analysisResult);
    	
    	if(jsonResult != null) {
    		analysisResult.setJsonObject(jsonResult);
    		role = analysisResult.generateRole(ideal);
    	} else {
    		role = analysisResult.generateNormalRole();
    	}
    	
    	role.setId(id);
    	roleService.addRole(role);
	}
}

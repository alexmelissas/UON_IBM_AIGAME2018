package springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.domain.Ideal;
import springboot.domain.IdealRepository;

@Service("idealService")
public class IdealService {
	@Autowired
	private IdealRepository idealRepository;
	
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
}

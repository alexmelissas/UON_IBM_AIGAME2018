package springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.domain.Ideal;
import springboot.domain.IdealRepository;
import springboot.domain.Player;
import springboot.util.AnalysisResult;
import springboot.util.PlayerConfig;

@Service("idealService")
public class IdealService {
	@Autowired
	private IdealRepository idealRepository;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private RedisService redisService;

	public void addIdeal(Ideal ideal) {
		idealRepository.save(ideal);
	}

	public Iterable<Ideal> getIdeals() {
		return idealRepository.findAll();
	}

	public Ideal getIdealById(String id) {
		Ideal ideal = null;
		Optional<Ideal> refIdeal = idealRepository.findById(id);
		if (refIdeal.isPresent()) {
			ideal = refIdeal.get();
		}
		return ideal;
	}

	public void initialIdeal(String id, Ideal newIdeal) {
		// This method will only be called once when the user submit the ideal
		// personality

		idealRepository.findById(id).map(ideal -> {
			ideal.setAgreeableness(newIdeal.getAgreeableness());
			ideal.setConscientiousness(newIdeal.getConscientiousness());
			ideal.setEmotionalrange(newIdeal.getEmotionalrange());
			ideal.setExtraversion(newIdeal.getExtraversion());
			ideal.setOpeness(newIdeal.getOpeness());

			this.initialPlayer(id, ideal);
			return idealRepository.save(ideal);
		});
	}

	public void deleteIdealById(String id) {
		idealRepository.deleteById(id);
	}

	public void initialPlayer(String id, Ideal ideal) {
		AnalysisResult analysisResult = new AnalysisResult();
		String jsonResult = redisService.getResult(id);
		Player player = playerService.getPlayerById(id);
		
		if (ideal.isAuth()) {
			if (jsonResult == null) {
				// Get the tweets again
				
			} else {

				
			}
		} else {
			
		}

		// TODO exceed the time
		/*
		 * if token: if !json: reget else: no auth
		 */

		System.out.println("------" + analysisResult);

		if (player == null) {
			// Player do not exist
		} else if (jsonResult != null) {
			analysisResult.setJsonObject(jsonResult);
			analysisResult.generateFactor(ideal, player);
		} else {
			analysisResult.generateNormalFactor(player);
		}

		player.setAttributes(PlayerConfig.getBasicStatus(player.level));
		player.applyPersonality();
		System.out.println(">>>>>>" + player);

		playerService.updatePlayer(id, player);
	}
}

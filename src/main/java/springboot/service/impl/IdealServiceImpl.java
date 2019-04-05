package springboot.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.config.PlayerConfig;
import springboot.domain.Ideal;
import springboot.domain.Player;
import springboot.repository.IdealRepository;
import springboot.service.IdealService;
import springboot.service.PlayerService;
import springboot.util.AnalysisResult;

/**
 * <p>
 * Implementation of {@link IdealService}
 * </p>
 * 
 * @author chenyu
 *
 */
@Service("idealService")
public class IdealServiceImpl implements IdealService {
	@Autowired
	private IdealRepository idealRepository;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private TwitterService twitterService;
	private static Logger logger = LoggerFactory.getLogger(IdealServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.IdealService#addIdeal(springboot.domain.Ideal)
	 */
	@Override
	public void addIdeal(Ideal ideal) {
		idealRepository.save(ideal);
		logger.info(">>>Create new ideal [ideal:{}]", ideal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.IdealService#getIdeals()
	 */
	@Override
	public Iterable<Ideal> getIdeals() {
		return idealRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.IdealService#getIdealById(java.lang.String)
	 */
	@Override
	public Ideal getIdealById(String id) {
		Ideal ideal = null;
		Optional<Ideal> refIdeal = idealRepository.findById(id);
		if (refIdeal.isPresent()) {
			ideal = refIdeal.get();
		}
		return ideal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.IdealService#initialIdeal(java.lang.String,
	 * springboot.domain.Ideal)
	 */
	@Override
	public void initialIdeal(String id, Ideal newIdeal) {
		// This method will only be called once when the user submit the ideal
		// personality
		logger.info(">>>Init the ideal [id:{}]", id);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.IdealService#deleteIdealById(java.lang.String)
	 */
	@Override
	public void deleteIdealById(String id) {
		idealRepository.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.IdealService#initialPlayer(java.lang.String,
	 * springboot.domain.Ideal)
	 */
	@Override
	public void initialPlayer(String id, Ideal ideal) {
		logger.info(">>>Init the player");
		AnalysisResult analysisResult = new AnalysisResult();
		String jsonResult = redisService.getResult(id);
		Player player = playerService.getPlayerById(id);

		if (ideal.isAuth() && jsonResult == null) {
			twitterService.reAnalysisTweets(id);
			jsonResult = redisService.getResult(id);
		}

		if (player == null) {
			// Player do not exist
		} else if (jsonResult != null) {
			analysisResult.setJsonObject(jsonResult);
			analysisResult.generateFactor(ideal, player);
		} else {
			analysisResult.generateNormalFactor(player);
		}

		player.setAttributes(PlayerConfig.getBasicStatus(player.getLevel()));
		player.applyPersonality();

		playerService.updatePlayer(id, player);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.IdealService#isExist(java.lang.String)
	 */
	@Override
	public boolean isExist(String id) {
		return idealRepository.existsById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.IdealService#unAuth(java.lang.String)
	 */
	@Override
	public void unAuth(String id) {
		idealRepository.findById(id).map(ideal -> {
			ideal.setAuth(false);
			logger.info(">>>Set the ideal as unauthorized");

			return idealRepository.save(ideal);
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see springboot.service.IdealService#reAuth(java.lang.String)
	 */
	@Override
	public void reAuth(String id) {
		idealRepository.findById(id).map(ideal -> {
			ideal.setAuth(true);
			logger.info(">>>Set the ideal as authorized");

			return idealRepository.save(ideal);
		});
	}
}

package springboot.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import springboot.domain.Player;
import springboot.service.PlayerService;

/**
 * <p>
 * The RedisService provides the temporary cache of data
 * </p>
 * 
 * @author chenyu
 *
 */
@Service("redisService")
public class RedisService implements InitializingBean {
	@Autowired
	private JedisPool jedisPool;
//	@Autowired
//	private PlayerService playerService;
	private Jedis jedis;

	/**
	 * Store the result of analysis for one hour
	 * 
	 * @param id     the id
	 * @param result the analysis result
	 */
	public void setResult(String id, String result) {
		jedis.set("result_" + id, result, "NX", "EX", 60 * 10);
	}

	/**
	 * Get the analysis result
	 * 
	 * @param id
	 * @return
	 */
	public String getResult(String id) {
		return jedis.get("result_" + id);
	}

	/**
	 * Add the battle account of player with input id
	 * 
	 * @param id the id
	 */
	public void addBattleCount(String id) {
		int count = this.getBattleCount(id);
		if (count >= 10) {
			return;
		}
		count++;
		this.setBattleCount(id, count);
	}

	/**
	 * Set the number of battles of a player
	 * 
	 * @param id    the id
	 * @param count the number of battls
	 */
	public void setBattleCount(String id, int count) {
		String str = Integer.toString(count);
		jedis.set("count_" + id, str);
		Date currentDate = new Date();
		LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault()).plusDays(1)
				.withHour(0).withMinute(0).withSecond(0).withNano(0);
		LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
		long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
		jedis.expire("count_" + id, (int) seconds);
	}

	/**
	 * Get the number of battles of a player
	 * 
	 * @param id the id
	 * @return the number of battles
	 */
	public int getBattleCount(String id) {
		int count = 0;
		if (jedis.exists("count_" + id)) {
			String str = jedis.get("count_" + id);
			count = Integer.parseInt(str);
		}
		return count;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.jedis = jedisPool.getResource();
		// TODO
//		List<Player> players = playerService.getPlayers();
//		this.jedis.set("players", SerializationUtils.serialize(players));
	}
}

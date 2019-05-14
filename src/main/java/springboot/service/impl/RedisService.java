package springboot.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

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
	protected static ReentrantLock lockJedis = new ReentrantLock();

//	@Autowired
//	private PlayerService playerService;

	/**
	 * Store the result of analysis for one hour
	 * 
	 * @param id     the id
	 * @param result the analysis result
	 */
	public void setResult(String id, String result) {
		Jedis jedis = jedisPool.getResource();
		jedis.set("result_" + id, result, "NX", "EX", 60 * 10);
		this.returnResource(jedis);
	}

	/**
	 * Get the analysis result
	 * 
	 * @param id the id
	 * @return the analysis result
	 */
	public String getResult(String id) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get("result_" + id);
		this.returnResource(jedis);
		return result;
	}

	/**
	 * Add the battle account of player with input id
	 * 
	 * @param id  the id
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
	 * Add the ranked battle account of player with input id
	 * 
	 * @param id  the id
	 */
	public void addRankedBattleCount(String id) {
		int count = this.getRankedBattleCount(id);
		if (count >= 5) {
			return;
		}
		count++;
		this.setRankedBattleCount(id, count);
	}

	/**
	 * Set the number of battles of a player
	 * 
	 * @param id    the id
	 * @param count the number of battls
	 */
	public void setBattleCount(String id, int count) {
		Jedis jedis = jedisPool.getResource();
		jedis.set("count_" + id, Integer.toString(count));
		int seconds = this.daily();
		jedis.expire("count_" + id, (int) seconds);
		this.returnResource(jedis);
	}
	
	/**
	 * Set the number of ranked battles of a player
	 * 
	 * @param id    the id
	 * @param count the number of battls
	 */
	public void setRankedBattleCount(String id, int count) {
		Jedis jedis = jedisPool.getResource();
		jedis.set("count_ranked_" + id, Integer.toString(count));
		int seconds = this.daily();
		jedis.expire("count_ranked_" + id, (int) seconds);
		this.returnResource(jedis);
	}

	/**
	 * Get the number of battles of a player
	 * 
	 * @param id  the id
	 * @return the number of battles
	 */
	public int getBattleCount(String id) {
		Jedis jedis = jedisPool.getResource();
		int count = 0;
		if (jedis.exists("count_" + id)) {
			String num = jedis.get("count_" + id);
			count = Integer.parseInt(num);
		}
		this.returnResource(jedis);
		return count;
	}
	
	/**
	 * Get the number of ranked battles of a player
	 * 
	 * @param id  the id
	 * @return the number of battles
	 */
	public int getRankedBattleCount(String id) {
		Jedis jedis = jedisPool.getResource();
		int count = 0;
		if (jedis.exists("count_ranked_" + id)) {
			String num = jedis.get("count_ranked_" + id);
			count = Integer.parseInt(num);
		}
		this.returnResource(jedis);
		return count;
	}

	/**
	 * Set the ranked score of a player
	 * 
	 * @param id    the id
	 * @param score the score of player
	 */
	public void setRankedScore(String id, int score) {
		Jedis jedis = jedisPool.getResource();
		jedis.set("score_" + id, Integer.toString(score));
		int seconds = this.weekly();
		jedis.expire("score_" + id, (int) seconds);
		this.returnResource(jedis);
	}

	/**
	 * Get the ranked score of a player
	 * 
	 * @param id the id
	 * @return the score of the player
	 */
	public int getRankedScore(String id) {
		Jedis jedis = jedisPool.getResource();
		int score = 0;
		if (jedis.exists("score_" + id)) {
			String str = jedis.get("score_" + id);
			score = Integer.parseInt(str);
		}
		this.returnResource(jedis);
		return score;
	}

	/**
	 * Set the score of a group
	 * 
	 * @param groupNum the group number
	 * @param score    the score
	 */
	public void setGroupScore(int groupNum, int score) {
		Jedis jedis = jedisPool.getResource();
		jedis.set("group_" + Integer.toString(groupNum), Integer.toString(score));
		int seconds = this.weekly();
		jedis.expire("group_" + Integer.toString(groupNum), seconds);
		this.returnResource(jedis);
	}

	/**
	 * Get the score of a group
	 * 
	 * @param groupNum the group number
	 * @return the score
	 */
	public int getGroupScore(int groupNum) {
		Jedis jedis = jedisPool.getResource();
		int score = 0;
		if (jedis.exists("group_" + Integer.toString(groupNum))) {
			String str = jedis.get("group_" + Integer.toString(groupNum));
			score = Integer.parseInt(str);
		}
		this.returnResource(jedis);
		return score;
	}

	/**
	 * Get the remaining seconds till the end of this day
	 * 
	 * @return the seconds
	 */
	public int daily() {
		Date currentDate = new Date();
		LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault()).plusDays(1)
				.withHour(0).withMinute(0).withSecond(0).withNano(0);
		LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
		long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
		return (int) seconds;
	}

	/**
	 * Get the remaining seconds till the end of this week
	 * 
	 * @return the seconds
	 */
	public int weekly() {
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(currentDate);
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		weekday = weekday == 1 ? 7 : weekday - 1;

		LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault()).plusDays(1)
				.withHour(0).withMinute(0).withSecond(0).withNano(0);
		LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
		long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
		seconds = seconds + (7 - weekday) * 24 * 60 * 60;
		return (int) seconds;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO
//		List<Player> players = playerService.getPlayers();
//		this.jedis.set("players", SerializationUtils.serialize(players));
	}

	/**
	 * Return jedis resource
	 * 
	 * @param jedis the jedis
	 */
	@SuppressWarnings("deprecation")
	public void returnResource(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}
}

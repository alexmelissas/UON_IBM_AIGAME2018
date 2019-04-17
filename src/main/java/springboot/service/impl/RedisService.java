package springboot.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
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
	 * @param id  the id
	 * @param str string indicate if the battle is ranked
	 */
	public void addBattleCount(String id, String str) {
		int count = this.getBattleCount(id, str);
		if ("".equals(str)) {
			if (count >= 10) {
				return;
			}
		} else {
			if (count >= 5) {
				return;
			}
		}
		count++;
		this.setBattleCount(id, count, str);
	}

	/**
	 * Set the number of battles of a player
	 * 
	 * @param id    the id
	 * @param count the number of battls
	 * @param str   string indicate if the battle is ranked
	 */
	public void setBattleCount(String id, int count, String str) {
		jedis.set("count_" + str + id, Integer.toString(count));
		int seconds = this.daily();
		jedis.expire("count_" + str + id, (int) seconds);
	}

	/**
	 * Get the number of battles of a player
	 * 
	 * @param id  the id
	 * @param str string indicate if the battle is ranked
	 * @return the number of battles
	 */
	public int getBattleCount(String id, String str) {
		int count = 0;
		if (jedis.exists("count_" + str + id)) {
			String num = jedis.get("count_" + str + id);
			count = Integer.parseInt(num);
		}
		return count;
	}

	/**
	 * Set the ranked score of a player
	 * 
	 * @param id    the id
	 * @param score the score of player
	 */
	public void setRankedScore(String id, int score) {
		jedis.set("score_" + id, Integer.toString(score));
		int seconds = this.weekly();
		jedis.expire("score_" + id, (int) seconds);
	}

	/**
	 * Get the ranked score of a player
	 * 
	 * @param id the id
	 * @return the score of the player
	 */
	public int getRankedScore(String id) {
		int score = 0;
		if (jedis.exists("score_" + id)) {
			String str = jedis.get("score_" + id);
			score = Integer.parseInt(str);
		}
		return score;
	}

	/**
	 * Set the score of a group
	 * 
	 * @param groupNum the group number
	 * @param score    the score
	 */
	public void setGroupScore(int groupNum, int score) {
		jedis.set("group_" + Integer.toString(groupNum), Integer.toString(score));
		int seconds = this.weekly();
		jedis.expire("group_" + Integer.toString(groupNum), seconds);
	}

	/**
	 * Get the score of a group
	 * 
	 * @param groupNum the group number
	 * @return the score
	 */
	public int getGroupScore(int groupNum) {
		int score = 0;
		if (jedis.exists("group_" + Integer.toString(groupNum))) {
			String str = jedis.get("group_" + Integer.toString(groupNum));
			score = Integer.parseInt(str);
		}
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
		this.jedis = jedisPool.getResource();
		// TODO
//		List<Player> players = playerService.getPlayers();
//		this.jedis.set("players", SerializationUtils.serialize(players));
	}
}

package springboot.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service("redisService")
public class RedisService implements InitializingBean {
	@Autowired
	private JedisPool jedisPool;
	private Jedis jedis;

	public void setResult(String id, String result) {
		jedis.set("result_" + id, result, "NX", "EX", 60 * 10);
	}

	public String getResult(String id) {
		return jedis.get("result_" + id);
	}

	public void addBattleCount(String id) {
		int count = this.getBattleCount(id);
		if (count >= 10) {
			return;
		}
		count++;
		this.setBattleCount(id, count);
	}
	
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
	}
}

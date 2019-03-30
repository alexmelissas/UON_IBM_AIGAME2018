package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service("redisService")
public class RedisService {
	@Autowired
	private JedisPool jedisPool;
	
	public void setResult(String id, String result) {
		Jedis jedis = jedisPool.getResource();
		jedis.set("result_" + id, result, "NX", "EX", 60 * 60);
	}
	
	public String getResult(String id) {
		Jedis jedis = jedisPool.getResource();
		return jedis.get("result_" + id);
	}
	
	
}

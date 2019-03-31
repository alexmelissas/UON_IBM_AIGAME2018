package springboot.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import twitter4j.Twitter;

@Service("redisService")
public class RedisService implements InitializingBean {
	@Autowired
	private JedisPool jedisPool;
	private Jedis jedis;
	
	public void setResult(String id, String result) {
		jedis.set("result_" + id, result, "NX", "EX", 30);
	}
	
	public String getResult(String id) {
		return jedis.get("result_" + id);
	}
	
	public void setTwitter(String id, Twitter twitter) {
		Gson gson = new Gson();
		jedis.set("twitter_" + id, gson.toJson(twitter));
	}
	
	public Twitter getTwitter(String id) {
		Gson gson = new Gson();
		return gson.fromJson(jedis.get("twitter_" + id), Twitter.class);
	}
	
	public void delTwitter(String id) {
		jedis.del("twitter_" + id);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.jedis = jedisPool.getResource();
	}
}

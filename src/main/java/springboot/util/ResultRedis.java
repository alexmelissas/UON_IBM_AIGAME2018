package springboot.util;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import springboot.domain.Result;

@Repository
public class ResultRedis {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	// store
	public void add(String key, int time, Result result) {
		Gson gson = new Gson();
		// Set the valid time for redis
		redisTemplate.opsForValue().set(key, gson.toJson(result), time, TimeUnit.HOURS);
	}
	
	// get
	public Result get(String key) {
		Gson gson = new Gson();
		Result result = null;
		String resultJson = redisTemplate.opsForValue().get(key);
		
		if (!StringUtils.isEmpty(resultJson)) {
			result = gson.fromJson(resultJson, Result.class);
		}
		
		return result;
	}
	
	// delete
	public void delete(String key) {
		redisTemplate.opsForValue().getOperations().delete(key);
	}
}

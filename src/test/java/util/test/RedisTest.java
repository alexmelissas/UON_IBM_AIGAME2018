package util.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import springboot.util.RedisConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RedisConfig.class })
public class RedisTest {
	private static Logger logger = LoggerFactory.getLogger(RedisTest.class);

	@Autowired
	private JedisPool jedisPool;

	@Before
	public void setup() {
		String id = "fake_id";
		String jsonResult = "Just for test";
		Jedis jedis = jedisPool.getResource();
		jedis.set(id, jsonResult);
	}

	@Test
	public void get() throws InterruptedException {
		String id = "fake_id";
		Jedis jedis = jedisPool.getResource();
		String jsonResult = jedis.get("fake_id");
		logger.info("======Result====== id:{}, result:{}", id, jsonResult);
	}

	@After
	public void delete() {
		String id = "fake_id";
		Jedis jedis = jedisPool.getResource();
		jedis.del("fake_id");
		String jsonResult = jedis.get("fake_id");
		logger.info("======Result====== id:{}, result:{}", id, jsonResult);
	}
}

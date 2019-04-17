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

import springboot.config.RedisConfig;
import springboot.service.impl.RedisService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RedisConfig.class, RedisService.class})
public class RedisTest {
	private static Logger logger = LoggerFactory.getLogger(RedisTest.class);

	@Autowired
	private RedisService redisService;

	@Before
	public void setup() {
		String id = "fake_id";
		String jsonResult = "Just for test";
		redisService.setResult(id, jsonResult);
	}

	@Test
	public void get() throws InterruptedException {
		String id = "fake_id";
		String jsonResult = redisService.getResult(id);
		logger.info("======Result====== id:{}, result:{}", id, jsonResult);
	}
	
	@Test
	public void countTest() {
		String id = "123";
		System.out.println(">>>" + redisService.getBattleCount(id, ""));
		id = "test_id";
		System.out.println(">>>" + redisService.getBattleCount(id, ""));
		
		id = "123";
		redisService.addBattleCount(id, "");
		System.out.println(">>>" + redisService.getBattleCount(id, ""));
		id = "test_id";
		redisService.addBattleCount(id, "");
		System.out.println(">>>" + redisService.getBattleCount(id, ""));
		id = "1233";
		redisService.addBattleCount(id, "ranked_");
		System.out.println(">>>" + redisService.getBattleCount(id, "ranked_"));
	}
	
	@Test
	public void scoreTest() {
		String id = "123";
		System.out.println(">>>" + redisService.getRankedScore(id));
		
		redisService.setRankedScore(id, 10);
		System.out.println(">>>" + redisService.getRankedScore(id));
	}

	@After
	public void delete() {
		String id = "fake_id";
		String jsonResult = redisService.getResult(id);
		logger.info("======Result====== id:{}, result:{}", id, jsonResult);
	}
}


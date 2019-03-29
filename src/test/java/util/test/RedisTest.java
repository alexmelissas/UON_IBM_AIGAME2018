package util.test;

import org.apache.tomcat.jni.Time;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springboot.domain.Result;
import springboot.util.ResultRedis;
import springboot.util.RedisConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisConfig.class, ResultRedis.class})
public class RedisTest {
	private static Logger logger = LoggerFactory.getLogger(RedisTest.class);
	
	@Autowired
	ResultRedis resultRedis;
	
	@Before
	public void setup() {
		Result result = new Result();
		result.setId("fake_id");
		result.setJsonResult("Just for test");
		
		resultRedis.add(result.getId(), 1, result);
	}
	
	@Test
	public void get() throws InterruptedException {
		Result result = resultRedis.get("fake_id");
		logger.info("======Result====== id:{}, result:{}", result.getId(), result.getJsonResult());
		Thread.sleep(5000);
		result = resultRedis.get("fake_id");
		System.out.println("Result: " + result);
	}
	
	@Test
	public void delete() {
		resultRedis.delete("fake_id");
		Result result = resultRedis.get("fake_id");
		System.out.println("Result: " + result);
	}
}

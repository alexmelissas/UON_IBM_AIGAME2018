package springboot.web;

import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import springboot.domain.Bot;
import springboot.domain.Player;
import springboot.service.impl.BattleService;

@RestController
public class BattleController {
	@Autowired
	private BattleService battleService;
	private static Logger logger = LoggerFactory.getLogger(BattleController.class);
	
	@GetMapping("/battle/{id}")
	public Player getRandomPlayer(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
		if (!battleService.isExist(id)) {
			try {
				request.getRequestDispatcher("/404").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		return battleService.getRandomPlayer(id);
	}

	@GetMapping("/battle/{difficult}/{id}")
	public Bot getBot(HttpServletRequest request, HttpServletResponse response, @PathVariable String difficult, @PathVariable String id) {
		if (!battleService.isExist(id)) {
			try {
				request.getRequestDispatcher("/404").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		return battleService.getBot(id, difficult);
	}

	@PutMapping("/battle")
	public Player handleResult(@RequestBody String jsonString) {
		logger.info("======Battle Result======");
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(jsonString);
		String id1 = jsonObject.get("id1").getAsString();
		String id2 = jsonObject.get("id2").getAsString();
		// true: id1 win; false: id1 lost;
		boolean result = jsonObject.get("result").getAsBoolean();
		int additionalExp = jsonObject.get("additionalExp").getAsInt();
		int additionalMoney = jsonObject.get("additionalMoney").getAsInt();

		Player player = battleService.handleResult(id1, id2, result, additionalExp, additionalMoney);		
		logger.info("======Battle Result End======");
		return player;
	}
}
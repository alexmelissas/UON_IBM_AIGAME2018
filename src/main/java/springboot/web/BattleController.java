package springboot.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import springboot.domain.Bot;
import springboot.domain.Player;
import springboot.service.BattleService;

@RestController
public class BattleController {
	@Autowired
	private BattleService battleService;

	@GetMapping("/battle/{id}")
	public Player getRandomPlayer(@PathVariable String id) {
		return battleService.getRandomPlayer(id);
	}

	@GetMapping("/battle/{difficult}/{id}")
	public Bot getBot(@PathVariable String difficult, @PathVariable String id) {
		return battleService.getBot(id, difficult);
	}

	@PutMapping("/battle")
	public Player handleResult(@RequestBody String jsonString) {
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(jsonString);
		String id1 = jsonObject.get("id1").getAsString();
		String id2 = jsonObject.get("id2").getAsString();
		// true: id1 win; false: id1 lost;
		boolean result = jsonObject.get("result").getAsBoolean();
		int additionalExp = jsonObject.get("additionalExp").getAsInt();
		int additionalMoney = jsonObject.get("additionalMoney").getAsInt();

		System.out.println(id1 + " " + id2 + " " + result + " " + additionalExp + " " + additionalMoney);

		// TODO update the result of player
		return battleService.handleResult(id1, id2, result, additionalExp, additionalMoney);
	}
}
package springboot.web;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import springboot.domain.Player;

@RestController
public class BattleController {
	@PutMapping("/battle")
	public Player handleBattleResult(@RequestBody String jsonString) {
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(jsonString);
		String id1 = jsonObject.get("id1").getAsString();
		String id2 = jsonObject.get("id2").getAsString();
		// true: id1 win; false: id1 lost;
		boolean  result = jsonObject.get("result").getAsBoolean();
		
		System.out.println(id1 + " " + id2 + " " + result);
		// TODO update the result of player
		return null;
	}
	
}

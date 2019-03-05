package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.domain.Player;

@Service("battleService")
public class BattleService {
	@Autowired
	private PlayerService playerService;
	
	public Player handleResult(String id1, String id2, boolean result, int additionalExp, int additionalMoney) {
		Player player1 = playerService.getPlayerById(id1);
		int exp = additionalExp;
		int money = additionalMoney;
		
		if(result) {
			// player win
			exp += 20;
			money += 50;
			player1.setWin(player1.getWin() + 1);
		} else {
			// player lose
			exp += 5;
			money += 10;
			player1.setLose(player1.getLose() + 1);
		}
		player1.setExperience(exp + player1.getExperience());
		player1.setMoney(money + player1.getMoney());
		
		player1.levelUp();
		playerService.updatePlayer(id1, player1);
		
		return player1;
	}
}

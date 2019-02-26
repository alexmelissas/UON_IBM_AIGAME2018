package springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import springboot.domain.Player;
import springboot.domain.PlayerRepository;

@Service("playerService")
public class PlayerService {
	@Autowired
	private PlayerRepository playerRepository;
	
	public void addPlayer(Player player) {
		playerRepository.save(player);
	}
	
	public Iterable<Player> getPlayers() {
		return playerRepository.findAll();
	}
	
	public Optional<Player> getPlayerById(String id) {
		return playerRepository.findById(id); 
	}
	
	public List<Player> getTopPlayers() {
		Sort sort = new Sort(Direction.DESC, "score");
		List<Player> players = playerRepository.findAll(sort);
		ArrayList<Player> topPlayers = new ArrayList<Player>(); 
		int count = 0;
		for(Player p : players) {
			if(count >= 5) {
				break;
			}
			topPlayers.add(p);
			count++;
		}
		return topPlayers;
	}
	
	public int getRankById(String id) {
		Sort sort = new Sort(Direction.DESC, "score");
		List<Player> players = playerRepository.findAll(sort);
		int count = 0;
		for(Player p : players) {
			if(p.getId().equals(id)) {
				break;
			}
			count++;
		}
		return count;
	}
	
	public void updatePlayer(String id, Player newPlayer) {
		playerRepository.findById(id)
						.map(player -> {
							player.setHp(newPlayer.getHp());
							player.setAttack(newPlayer.getAttack());
							player.setDefense(newPlayer.getDefense());
							player.setAgility(newPlayer.getAgility());
							player.setCriticalStrike(newPlayer.getCriticalStrike());

							// TODO check the update of player
							// long way to go :(
							return playerRepository.save(player);
						});
	}
	
	public void deletePlayerById(String id) {
		playerRepository.deleteById(id);
		
	}
}

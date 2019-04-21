import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springboot.Application;
import springboot.domain.Player;
import springboot.service.PlayerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PlayerServiceTest {
	@Autowired
	private PlayerService playerService;
	private String id = "player";
	private int level = 10;
	private int group = 1;
	private double factor = 1;

	@Before
	public void testAddPlayer() {
		Player player = new Player();
		player.setId(id);
		player.setLevel(level);
		player.setGroup(group);
		player.setFactor(factor);
		playerService.addPlayer(player);
	}

	@After
	public void testDeletePlayerById() {
		playerService.deletePlayerById(id);
		Player player = playerService.getPlayerById(id);
		assertTrue(player == null);
	}

	@Test
	public void testGetPlayers() {
		Player player = playerService.getPlayerById(id);
		assertTrue(player != null);
	}

	@Test
	public void testGetPlayersByLevel() {
		List<Player> players = playerService.getPlayersByLevel(level);
		assertTrue(players != null);
	}

	@Test
	public void testUpdatePlayer() {
		Player player = playerService.getPlayerById(id);
		player.setFactor(factor + 0.5);
		playerService.updatePlayer(id, player);
		player = playerService.getPlayerById(id);
		assumeTrue(player.getFactor() > factor + 0.49);
		assumeTrue(player.getFactor() < factor + 0.51);
	}

	@Test
	public void testGetPlayersByGroup() {
		List<Player> players = playerService.getPlayersByGroup(group);
		assertTrue(players != null);
	}
}

import springboot.config.PlayerConfig;

public class PlayerConfigTest {
	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			System.out.println("level: " + i + " agility: " + PlayerConfig.getBasicStatus(i)[3]);
		}
	}
}

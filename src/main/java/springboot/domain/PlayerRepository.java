package springboot.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String>{
	public List<Player> findAllByLevel(int levele);
}

package springboot.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * The repository of Player which extends {@link JpaRepository}
 * </p>
 * 
 * @author Yu Chen
 *
 */
public interface PlayerRepository extends JpaRepository<Player, String> {
	public List<Player> findAllByLevel(int levele);
}

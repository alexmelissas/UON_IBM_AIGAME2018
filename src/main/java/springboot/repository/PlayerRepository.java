package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.domain.Player;

/**
 * <p>
 * The repository of Player which extends {@link JpaRepository}.
 * </p>
 * 
 * @author chenyu
 *
 */
public interface PlayerRepository extends JpaRepository<Player, String> {
	public List<Player> findAllByLevel(int levele);
}

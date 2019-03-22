package springboot.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * The repository of User which extends {@link JpaRepository}
 * </p>
 * 
 * @author Yu Chen
 *
 */
public interface UserRepository extends JpaRepository<User, String> {
	public Optional<User> findByUsername(String username);

	public boolean existsByUsername(String username);
}

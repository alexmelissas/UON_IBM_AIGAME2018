package springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.domain.User;

/**
 * <p>
 * The repository of User which extends {@link JpaRepository}.
 * </p>
 * 
 * @author chenyu
 *
 */
public interface UserRepository extends JpaRepository<User, String> {
	public Optional<User> findByUsername(String username);

	public boolean existsByUsername(String username);
}

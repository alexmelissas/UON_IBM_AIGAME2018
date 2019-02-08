package springboot.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer>{
	public Optional<User> findByUid(String Uid);
	public Optional<User> findByUsername(String username);
	public boolean existsByUsername(String username);
	@Transactional
	public void deleteByUid(String uid);
}

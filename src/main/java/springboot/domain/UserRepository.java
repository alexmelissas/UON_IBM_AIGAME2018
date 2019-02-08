package springboot.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
	// TODO
	public Optional<User> findByUid(String Uid);
	public boolean existsByUsername(String username);
}

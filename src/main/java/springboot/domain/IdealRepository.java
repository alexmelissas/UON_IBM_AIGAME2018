package springboot.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * The repository of Ideal which extends {@link JpaRepository}
 * </p>
 * 
 * @author Yu Chen
 *
 */
public interface IdealRepository extends JpaRepository<Ideal, String> {

}

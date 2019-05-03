package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.domain.Ideal;

/**
 * <p>
 * The repository of Ideal which extends {@link JpaRepository}.
 * </p>
 * 
 * @author chenyu
 *
 */
public interface IdealRepository extends JpaRepository<Ideal, String> {

}

package itc.hoseo.cc.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import itc.hoseo.cc.domain.Rating;
import itc.hoseo.cc.domain.User;

/**
 * Sample JPA Repository
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html
 * @author pjh04
 *
 */
public interface RatingRepository extends CrudRepository<Rating, String> {
	List<Rating> findByRateUser(User user);
	List<Rating> findByReceiveUser(User user);
}


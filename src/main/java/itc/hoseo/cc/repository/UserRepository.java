package itc.hoseo.cc.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import itc.hoseo.cc.domain.User;

/**
 * Sample JPA Repository
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html
 * @author pjh04
 *
 */
public interface UserRepository extends CrudRepository<User, String> {
	
	User findByNickname(String nickname);
	//페이징
	List<User> findAll(Pageable page);
	//Like 검색
	List<User> findByNicknameContains(String nickname, Pageable page);
}


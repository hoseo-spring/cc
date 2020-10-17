package itc.hoseo.cc.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import itc.hoseo.cc.domain.Comment;
import itc.hoseo.cc.domain.User;


public interface CommentRepository extends CrudRepository<Comment, Long> {
	List<Comment> findByUser(User user, Pageable page);
	List<Comment> findByProduct(User user, Pageable page);
}


package itc.hoseo.cc.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import itc.hoseo.cc.domain.Comment;
import itc.hoseo.cc.domain.Product;
import itc.hoseo.cc.domain.User;


public interface CommentRepository extends CrudRepository<Comment, Long> {
	List<Comment> findBySendUserId(String sendUserId, Pageable page);
	List<Comment> findByReceiveUserId(String receiveUserId, Pageable page);
	List<Comment> findBySendUserId(String sendUserId);
	List<Comment> findByReceiveUserId(String receiveUserId);
	List<Comment> findByProductIdAndReceiveUserId(String productId, String receiveUserId, Pageable page);
	List<Comment> findByProductIdAndReceiveUserId(String productId, String receiveUserId);
}


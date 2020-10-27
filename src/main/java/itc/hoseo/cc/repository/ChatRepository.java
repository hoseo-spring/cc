package itc.hoseo.cc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import itc.hoseo.cc.domain.ChatMessage;
import itc.hoseo.cc.domain.Locations;

public interface ChatRepository extends CrudRepository<ChatMessage, String> {
	//
	List<ChatMessage> findBySenderId(String senderId);
	List<ChatMessage> findByReceiverId(String receiverId);
	List<ChatMessage> findByProductId(String productId);
	List<ChatMessage> findByWs(String Ws);
}

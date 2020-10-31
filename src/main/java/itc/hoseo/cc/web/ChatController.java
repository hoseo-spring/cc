package itc.hoseo.cc.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import itc.hoseo.cc.domain.ChatMessage;
import itc.hoseo.cc.domain.Comment;
import itc.hoseo.cc.repository.ChatRepository;
import itc.hoseo.cc.repository.CommentRepository;
import itc.hoseo.cc.repository.ProductRepository;
import itc.hoseo.cc.repository.UserRepository;

@Controller
public class ChatController {
	@Autowired
	ChatRepository chatRepo;
	@Autowired
	ProductRepository productRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	CommentRepository commentRepo;
	
	@MessageMapping("/chat/send/{productId}.{senderId}")
	@SendTo("/topic/recv/{productId}.{senderId}")
	public ChatMessage sendMessage(@Payload ChatMessage msg) {
		msg.setSendDttm(new Date());
		msg.setWs(msg.getProductId()+"."+msg.getReceiverId());
		chatRepo.save(msg);
		return msg;
	}
	
	@RequestMapping(path = "/chat", method = RequestMethod.GET)
	public String messageStart(ModelMap mm, String product_id, String seller_id, String opponent_id) {
		mm.put("product", productRepo.findById(Long.parseLong(product_id)).get());
		mm.put("chats", chatRepo.findByWs(product_id+"."+seller_id));
		return "chat";
	}
	
	@RequestMapping(path = "/comment", method = RequestMethod.POST)
	public String sendComment(ModelMap mm, String star, String content, String sendUserId, String receiveUserId, String productId, String sellerId) {
		commentRepo.save(
					Comment.builder().rate(Double.parseDouble(star)).content(content).sendUserId(sendUserId).receiveUserId(receiveUserId).productId(productId).uploadDate(new Date()).build()
				);
		return "redirect:/chat?product_id="+productId+"&seller_id="+sellerId+"&opponent_id="+receiveUserId;
	}
}

package itc.hoseo.cc.web;

import java.security.Principal;
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
import itc.hoseo.cc.domain.Product;
import itc.hoseo.cc.domain.User;
import itc.hoseo.cc.repository.ChatRepository;
import itc.hoseo.cc.repository.CommentRepository;
import itc.hoseo.cc.repository.ProductRepository;
import itc.hoseo.cc.repository.UserRepository;
import itc.hoseo.cc.service.SpringSecurityUserContext;

@Controller
public class ChatController {
	@Autowired
	SpringSecurityUserContext userContext;
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
		msg.setProductName(productRepo.findById(Long.parseLong(msg.getProductId())).get().getName());
		msg.setSellerId(productRepo.findById(Long.parseLong(msg.getProductId())).get().getUser().getId());
		if(msg.getSellerId().equals(msg.getSenderId())) {
			msg.setWs(msg.getProductId()+"."+msg.getReceiverId());
		} else {
			msg.setWs(msg.getProductId()+"."+msg.getSenderId());
		}
		chatRepo.save(msg);
		return msg;
	}
	
	@RequestMapping(path = "/chat", method = RequestMethod.GET)
	public String messageStart(ModelMap mm, Principal principal, String product_id, String seller_id, String opponent_id) {
		mm.put("product", productRepo.findById(Long.parseLong(product_id)).get());
		if(seller_id.equals(principal.getName())) {
			mm.put("chats", chatRepo.findByWs(product_id+"."+opponent_id));
		} else {
			mm.put("chats", chatRepo.findByWs(product_id+"."+principal.getName()));
		}
		return "chat";
	}
	
	@RequestMapping(path = "/comment", method = RequestMethod.POST)
	public String sendComment(ModelMap mm, String star, String content, String sendUserId, String receiveUserId, String productId, String sellerId) {
		commentRepo.save(
					Comment.builder().rate(Double.parseDouble(star)).content(content).sendUserId(sendUserId).receiveUserId(receiveUserId).productId(productId).uploadDate(new Date()).build()
				);
		Product product = productRepo.findById(Long.parseLong(productId)).get();
		product.setSoldDate(new Date());
		productRepo.save(product);
		return "redirect:/chat?product_id="+productId+"&seller_id="+sellerId+"&opponent_id="+receiveUserId;
	}
}

package itc.hoseo.cc.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import itc.hoseo.cc.domain.ChatMessage;

@Controller
public class ChatController {

	@MessageMapping("/chat/send/{productId}.{senderId}")
	@SendTo("/topic/recv/{productId}.{senderId}")
	public ChatMessage sendMessage(@Payload ChatMessage msg) {
		return msg;
	}
	
}

package itc.hoseo.cc.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import itc.hoseo.cc.domain.ChatMessage;
import itc.hoseo.cc.domain.User;
import itc.hoseo.cc.repository.ChatRepository;
import itc.hoseo.cc.repository.ProductRepository;
import itc.hoseo.cc.repository.UserRepository;
import itc.hoseo.cc.service.SpringSecurityUserContext;
import itc.hoseo.cc.service.UserService;

@Controller
public class MypageController {

	@Autowired
	SpringSecurityUserContext userContext;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ChatRepository chatRepo;
	
	@RequestMapping(path = "/mypage", method = RequestMethod.GET) 
	public String mypageGet(ModelMap mm) {
		User user = userContext.getCurrentUser();
		productRepo.findByUserId(user.getId(), PageRequest.of(0, 5));
		mm.put("user", user);
		mm.put("product", productRepo.findByUserId(user.getId(), PageRequest.of(0, 5)));
		List<ChatMessage> chatsAll = chatRepo.findBySenderIdOrReceiverId(user.getId(), user.getId());
		List<String> wss = new ArrayList<>();
		List<ChatMessage> chats = new ArrayList<>();
		if(!chatsAll.isEmpty()) {
			for(ChatMessage chat : chatsAll) {
				if(wss.isEmpty()) {
					wss.add(chat.getWs());
					chats.add(chatRepo.findByWsOrderBySendDttmDesc(chat.getWs()).get(0));
				} else {
					if(!wss.contains(chat.getWs())) {
						wss.add(chat.getWs());
						chats.add(chatRepo.findByWsOrderBySendDttmDesc(chat.getWs()).get(0));
					}
				}
			}
		}
		mm.put("chats", chats);
		return "mypage";
	}
	
	@RequestMapping(path = "/edit", method = RequestMethod.POST)
	public String editPost(Model mm, @Valid User user, String id) {
		return "/mypage";
	}
}

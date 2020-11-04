package itc.hoseo.cc.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import itc.hoseo.cc.domain.ChatMessage;
import itc.hoseo.cc.domain.UploadFile;
import itc.hoseo.cc.domain.User;
import itc.hoseo.cc.repository.ChatRepository;
import itc.hoseo.cc.repository.FileRepository;
import itc.hoseo.cc.repository.ProductRepository;
import itc.hoseo.cc.repository.UserRepository;
import itc.hoseo.cc.service.SpringSecurityUserContext;

@Controller
public class MypageController {

	@Autowired
	SpringSecurityUserContext userContext;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	private FileRepository fileRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ChatRepository chatRepo;
	
	@Autowired
	private Environment env;
	
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
					ChatMessage c = null;
					int i = 0;
					boolean added = false;
					while(!added) {
						c = chatRepo.findByWsOrderBySendDttmDesc(chat.getWs()).get(i);
						i++;
						if(c.getContent() != null) {
							chats.add(c);
							added = true;
						}
					}
				} else {
					if(!wss.contains(chat.getWs())) {
						wss.add(chat.getWs());
						ChatMessage c = null;
						int i = 0;
						boolean added = false;
						while(!added) {
							c = chatRepo.findByWsOrderBySendDttmDesc(chat.getWs()).get(i);
							i++;
							if(c.getContent() != null) {
								chats.add(c);
								added = true;
							}
						}
					}
				}
			}
		}
		mm.put("chats", chats);
		return "mypage";
	}
	
	
	@RequestMapping(path="/edit",  method = RequestMethod.GET)
	public String editGet(ModelMap mm) {
		mm.put("user", userContext.getCurrentUser());
		return "edit";
	}
	
	
	@RequestMapping(path = "/edit", method = RequestMethod.POST)
	public String editPost(Model mm, @Valid User user, @RequestParam("img") List<MultipartFile> files) {
		User curUser = userContext.getCurrentUser(); 
		curUser.setPassword(user.getPassword());
		curUser.setNickname(user.getNickname());
		userRepo.save(curUser);
		
		final String uploadDir = env.getProperty("cc.uploaddir.profile");
		
		List<UploadFile> profile = files.stream()
				.map(f -> {
					UploadFile uf = new UploadFile();
					uf.setFileName(f.getOriginalFilename());
					
					String storedFileName = UUID.randomUUID().toString();
									
					while(Files.exists(Paths.get(uploadDir, storedFileName))) {
						storedFileName = UUID.randomUUID().toString();
					}
					
					uf.setStoredFileName(storedFileName);
					
					try {
						f.transferTo(Paths.get(uploadDir, storedFileName));
					} catch (IllegalStateException | IOException e) {
						throw new RuntimeException("파일업로드중 예외 발생", e);
					}
					
					return uf;
				}).collect(Collectors.toList());
		for(UploadFile p : profile) {
			System.out.println(p);
			if(!p.getFileName().equals("")) {
				fileRepo.save(p);
				curUser.setImages(profile);
				userRepo.save(curUser);
			}
		}
		
		locaRepo.deleteAll(locaRepo.findByUser(curUser));
		String[] address = {address0, address1, address2};
		for(int i = 0; i < address.length; i++) {
			if(!address[i].equals("-")) {
				Locations location = Locations.
						builder().
						state(address[i].split(" ")[0]).
						city(address[i].split(" ")[1]).
						user(curUser).
						build();
				locaRepo.save(location);
			}
		}
		
		return "redirect:/mypage";
	}
}

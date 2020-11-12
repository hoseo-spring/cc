package itc.hoseo.cc.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.PostLoad;
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
import itc.hoseo.cc.domain.Comment;
import itc.hoseo.cc.domain.Locations;
import itc.hoseo.cc.domain.UploadFile;
import itc.hoseo.cc.domain.User;
import itc.hoseo.cc.repository.ChatRepository;
import itc.hoseo.cc.repository.CommentRepository;
import itc.hoseo.cc.repository.FileRepository;
import itc.hoseo.cc.repository.LocationsRepository;
import itc.hoseo.cc.repository.ProductRepository;
import itc.hoseo.cc.repository.UserRepository;
import itc.hoseo.cc.service.SpringSecurityUserContext;
import itc.hoseo.cc.service.UserContext;

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
	CommentRepository commentRepo;
	
	@Autowired
	LocationsRepository locaRepo;
	
	@Autowired
	private Environment env;
	
	@PostConstruct
	public void init() {
		locaRepo.save(
			Locations.builder().city("송파구").state("서울특별시").user(userRepo.findByNickname("테스트")).build()
		);
		commentRepo.save(
			Comment.builder().productId("59").content("너무 친절하셔서 기분 좋게 거래했습니다.").rate(5.0).receiveUserId("rlacjswo").sendUserId("test").uploadDate(new Date()).build()
		);
		commentRepo.save(
				Comment.builder().productId("59").content("시간도 잘 지켜주시고 좋아요~").rate(4.5).receiveUserId("test").sendUserId("rlacjswo").uploadDate(new Date()).build()
			);
		commentRepo.save(
			Comment.builder().productId("64").content("좋았어요").rate(4.5).receiveUserId("ahrlqkrauf").sendUserId("test").uploadDate(new Date()).build()
		);
		commentRepo.save(
				Comment.builder().productId("64").content("좋아요").rate(4.0).receiveUserId("test").sendUserId("ahrlqkrauf").uploadDate(new Date()).build()
			);
		commentRepo.save(
				Comment.builder().productId("69").content("맛있게 먹을게요!!").rate(5.0).receiveUserId("rlawkddl").sendUserId("test").uploadDate(new Date()).build()
			);
		commentRepo.save(
				Comment.builder().productId("69").content("조금 늦으셨어요").rate(3.0).receiveUserId("test").sendUserId("rlawkddl").uploadDate(new Date()).build()
			);
		commentRepo.save(
				Comment.builder().productId("4").content("물건상태는 그냥 그런데 넘 친절하세요~").rate(4.0).receiveUserId("rkdxorhd2").sendUserId("test").uploadDate(new Date()).build()
			);
	}
	
	@RequestMapping(path = "/mypage", method = RequestMethod.GET) 
	public String mypageGet(ModelMap mm, @RequestParam(required = false) String productPage, @RequestParam(required = false) String commentPage) {
		User user = userContext.getCurrentUser();
		productRepo.findByUserId(user.getId(), PageRequest.of(0, 5));
		mm.put("user", user);
		if(productPage == null) {
			productPage = "0";
		}
		mm.put("product", productRepo.findByUserId(user.getId(), PageRequest.of(Integer.parseInt(productPage), 5)));
		if(commentPage == null) {
			commentPage = "0";
		}
		mm.put("comments", commentRepo.findByReceiveUserId(user.getId(), PageRequest.of(Integer.parseInt(productPage), 20)));
		
		int productCount = productRepo.findByUserId(user.getId()).size();
		int pPage = Integer.parseInt(productPage);
		
		int productWholePage = ((productCount % 5) == 0) ? (productCount / 5) : ((int)(productCount / 5)) + 1;
		int productPrev = (pPage < 5) ? 0 : (pPage - 5);
		int productNext = (pPage >= (productWholePage - 5)) ? productWholePage - 1 : (pPage + 5);
		mm.put("pPrev", productPrev);
		mm.put("pNext", productNext);
		mm.put("pWholePage", productWholePage);
		mm.put("pStartPage", ((int)(pPage/5)*5));
		mm.put("pPage", pPage);

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
		
		List<Comment> comments = commentRepo.findByReceiveUserId(user.getId());
		List<UploadFile> commentImages = new ArrayList<>();
		double avgRate = 0;
		int count = 0;
		if(!comments.isEmpty()) {
			for(Comment c : comments) {
				if(count == 0) {
					avgRate = c.getRate();
				} else {
					avgRate = ((avgRate * count) + c.getRate()) / (count + 1);
				}
				commentImages.addAll(userRepo.findById(c.getSendUserId()).get().getImages());
				count++;
			}
		}
		mm.put("avgRate", avgRate);
		mm.put("commentImages", commentImages);
		
		List<UploadFile> chatImages = new ArrayList<>();
		if(!chats.isEmpty()) {
			for(ChatMessage c : chats) {
				if(c.getReceiverId().equals(user.getId())) {
					chatImages.addAll(userRepo.findById(c.getSenderId()).get().getImages());
				} else {
					chatImages.addAll(userRepo.findById(c.getReceiverId()).get().getImages());
				}
			}
		}
		
		mm.put("chatImages", chatImages);
		return "mypage";
	}
	
	
	@RequestMapping(path="/edit",  method = RequestMethod.GET)
	public String editGet(ModelMap mm) {
		mm.put("user", userContext.getCurrentUser());
		mm.put("locations", locaRepo.findByUser(userContext.getCurrentUser()));
		return "edit";
	}
	
	
	@RequestMapping(path = "/edit", method = RequestMethod.POST)
	public String editPost(Model mm, @Valid User user, @RequestParam("img") List<MultipartFile> files, String address0, String address1, String address2) {
		User curUser = userContext.getCurrentUser();
		if(!user.getPassword().equals("")) {
			curUser.setPassword(user.getPassword());
		}
		curUser.setNickname(user.getNickname());
		
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
	
	@RequestMapping(path = "/profile", method = RequestMethod.GET) 
	public String profileGet(ModelMap mm, String user_id, @RequestParam(required = false) String productPage, @RequestParam(required = false) String commentPage) {
		User user = userRepo.findById(user_id).get();
		mm.put("user", user);
		if(productPage == null) {
			productPage = "0";
		}
		mm.put("product", productRepo.findByUserId(user.getId(), PageRequest.of(Integer.parseInt(productPage), 5)));
		if(commentPage == null) {
			commentPage = "0";
		}
		mm.put("comments", commentRepo.findByReceiveUserId(user.getId(), PageRequest.of(Integer.parseInt(productPage), 5)));
		
		int productCount = productRepo.findByUserId(user.getId()).size();
		int pPage = Integer.parseInt(productPage);
		
		int productWholePage = ((productCount % 5) == 0) ? (productCount / 5) : ((int)(productCount / 5)) + 1;
		int productPrev = (pPage < 5) ? 0 : (pPage - 5);
		int productNext = (pPage >= (productWholePage - 5)) ? productWholePage - 1 : (pPage + 5);
		mm.put("pPrev", productPrev);
		mm.put("pNext", productNext);
		mm.put("pWholePage", productWholePage);
		mm.put("pStartPage", ((int)(pPage/5)*5));
		mm.put("pPage", pPage);
		
		List<Comment> comments = commentRepo.findByReceiveUserId(user.getId());
		List<UploadFile> images = new ArrayList<>();
		double avgRate = 0;
		int count = 0;
		if(!comments.isEmpty()) {
			for(Comment c : comments) {
				if(count == 0) {
					avgRate = c.getRate();
				} else {
					avgRate = ((avgRate * count) + c.getRate()) / (count + 1);
				}
				images.addAll(userRepo.findById(c.getSendUserId()).get().getImages());
				count++;
			}
		}
		mm.put("avgRate", avgRate);
		mm.put("commentImages", images);
		return "profile";
	}
}

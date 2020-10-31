package itc.hoseo.cc.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.expression.Lists;

import itc.hoseo.cc.domain.Product;
import itc.hoseo.cc.domain.UploadFile;
import itc.hoseo.cc.domain.User;
import itc.hoseo.cc.repository.FileRepository;
import itc.hoseo.cc.repository.ProductRepository;
import itc.hoseo.cc.repository.UserRepository;
import itc.hoseo.cc.service.UserService;

@Controller
public class MainController {
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private FileRepository fileRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void init() {
		userService.createUser(User.builder().id("test").password("1234").nickname("테스트").registeredDate(new Date()).build());
		userService.createUser(User.builder().id("asdf1234").password("789").nickname("닉네임").registeredDate(new Date()).build());
		userService.createUser(User.builder().id("rlacjswo").password("12345").nickname("김천재").registeredDate(new Date()).build());
		userService.createUser(User.builder().id("test2").password("1234").nickname("기매미").registeredDate(new Date()).build());
		
		
		Product p1 = new Product(null, "스위치 네온 구형", "전자/가전", 200000, "옛날에 사두고 두어번 쓰고 안 쓴 제품입니다.",
				new Date(), null, userRepo.findByNickname("김천재"), "서울",  null, null);
		Product p2 = new Product(null, "파이썬 책", "도서", 7000, "중고감 약간 있는 파이썬 책 팝니다",
				new Date(), null, userRepo.findByNickname("테스트"), "서울",  null, null);
		Product p3 = new Product(null, "닌텐도 스위치 포켓몬스터 소드", "전자/가전", 25000, "별로 안썼구요 케이스 포함입니다",
				new Date(), null, userRepo.findByNickname("테스트"), "경기", null, null);
		Product p4 = new Product(null, "링피트", "전자/가전", 100000, "링, 게임팩만 팝니다. 본체 X",
				new Date(), null, userRepo.findByNickname("테스트"), "경기", null, null);
		Product p5 = Product.builder().name("청바지").category("의류").price(7000).description("인터넷으로 산 청바지입니다. 사이즈가 안맞아 팝니다. 사이즈 28입니다.")
				.uploadDate(new Date()).user(userRepo.findByNickname("닉네임")).location("부산").build();
		
		List<UploadFile> p1Imgs = new ArrayList<>();
		p1Imgs.add(UploadFile.builder().fileName("switch.png").storedFileName("switch.png").build());
		
		fileRepo.saveAll(p1Imgs);
		
		p1.setImages(p1Imgs);
		productRepo.save(p1);
		
		
		productRepo.save(p2);
		productRepo.save(p3);
		productRepo.save(p4);
		productRepo.save(p5);
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET) 
	public String indexGet(ModelMap mm) {
		return "index";
	}
	
	
	@RequestMapping(path = "/sign", method = RequestMethod.GET) 
	public String signGet(ModelMap mm) {
		return "sign";
	}

	


}

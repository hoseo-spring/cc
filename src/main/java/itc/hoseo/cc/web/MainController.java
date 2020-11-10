package itc.hoseo.cc.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		userService.createUser(User.builder().id("jammin").password("1234").nickname("잼민이").registeredDate(new Date()).build());
		userService.createUser(User.builder().id("ajdajddl").password("1234").nickname("멍멍이").registeredDate(new Date()).build());
		userService.createUser(User.builder().id("zjazjadl").password("1234").nickname("컴컴이").registeredDate(new Date()).build());
		userService.createUser(User.builder().id("rlawkddl").password("1234").nickname("김장이").registeredDate(new Date()).build());
		userService.createUser(User.builder().id("ahrlqkrauf").password("1234").nickname("모기박멸").registeredDate(new Date()).build());
		userService.createUser(User.builder().id("qkrcjswo").password("1234").nickname("이천재").registeredDate(new Date()).build());
		
		
		Product p1 = new Product(null, "스위치 네온 구형", "전자/가전", 200000, "옛날에 사두고 두어번 쓰고 안 쓴 제품입니다.",
				new Date(), null, userRepo.findByNickname("김천재"), "서울특별시 송파구",  null, null);
		Product p2 = new Product(null, "파이썬 책", "도서", 7000, "중고감 약간 있는 파이썬 책 팝니다",
				new Date(), null, userRepo.findByNickname("테스트"), "서울특별시 영등포구",  null, null);
		Product p3 = new Product(null, "닌텐도 스위치 포켓몬스터 소드", "전자/가전", 25000, "별로 안썼구요 케이스 포함입니다",
				new Date(), null, userRepo.findByNickname("테스트"), "경기도 안양시", null, null);
		Product p4 = new Product(null, "링피트", "전자/가전", 100000, "링, 게임팩만 팝니다. 본체 X",
				new Date(), null, userRepo.findByNickname("테스트"), "경기도 안산시", null, null);
		Product p5 = Product.builder().name("청바지").category("의류").price(5000).description("인터넷으로 산 청바지입니다. 사이즈가 안맞아 팝니다. 사이즈 28입니다.")
				.uploadDate(new Date()).user(userRepo.findByNickname("닉네임")).location("부산광역시 서구").build();
		Product p6 = Product.builder().name("짐볼").category("스포츠/레저").price(6000).description("사놓고 한 번도 안써서 팝니다~")
				.uploadDate(new Date()).user(userRepo.findByNickname("닉네임")).location("부산광역시 서구").build();
		Product p7 = Product.builder().name("트램펄린").category("스포츠/레저").price(15000).description("재밌어요")
				.uploadDate(new Date()).user(userRepo.findByNickname("김천재")).location("서울특별시 송파구").soldDate(new Date()).build();
		Product p8 = Product.builder().name("너무 예쁜 선인장~").category("가구/생활").price(3000).description("제가 3년 가까이 키운 선인장이에요~ \n싱싱하고 귀여워용! \n직접 오시면 반값에 드려요~")
				.uploadDate(new Date()).user(userRepo.findByNickname("기매미")).location("제주도 제주시").build();
		Product p9 = Product.builder().name("마스크 한통 팔음").category("의류").price(100000).description("좀 쓰고 다니라구")
				.uploadDate(new Date()).user(userRepo.findByNickname("김천재")).location("인천광역시 동구").build();
		Product p10 = Product.builder().name("에프킬라").category("가구/생활").price(2300).description("여름동안 저를 지켜준 고마운 에프킬라입니다..")
				.uploadDate(new Date()).user(userRepo.findByNickname("모기박멸")).location("강원도 속초시").soldDate(new Date()).build();
		Product p11 = Product.builder().name("같이 고깃집 갈사람 구함").category("기타").price(5000).description("같이 고기 먹으러 가요! 고기 제가 삽니다5000원은 제가 드리는거예요~")
				.uploadDate(new Date()).user(userRepo.findByNickname("이천재")).location("강원도 강릉시").build();
		Product p12 = Product.builder().name("중학교 2학년 쎈 수학").category("도서").price(10000).description("사놓고 한번도 안썼어요! 새것처럼 깨끗해요")
				.uploadDate(new Date()).user(userRepo.findByNickname("잼민이")).location("부산광역시 서구").soldDate(new Date()).build();
		Product p13 = Product.builder().name("먼나라 이웃나라 전권 판매").category("도서").price(30000).description("재밌어요")
				.uploadDate(new Date()).user(userRepo.findByNickname("닉네임")).location("서울특별시 서초구").soldDate(new Date()).build();
		Product p14 = Product.builder().name("복권 판매").category("기타").price(2000).description("수동이고요 돼지꿈 꿨습니다")
				.uploadDate(new Date()).user(userRepo.findByNickname("김천재")).location("충청남도 계룡시").build();
		Product p15 = Product.builder().name("강아지").category("기타").price(1000000000).description("그냥 자랑하려고 올립니다. 감탄하고 가세요!")
				.uploadDate(new Date()).user(userRepo.findByNickname("멍멍이")).location("경기도 고양시").build();
		Product p16 = Product.builder().name("구형 컴퓨터긴한데 잘 돌아감").category("전자/가전").price(35000).description("지뢰찾기 풀옵가능, 묶혀놨다가 유물로 재판매 가능")
				.uploadDate(new Date()).user(userRepo.findByNickname("컴컴이")).location("경상북도 경산시").build();
		Product p17 = Product.builder().name("김장김치 무료 나눔~~").category("기타").price(100).description("이번에 본가 가서 김장한거 무료나눔해요~~~ ^,,,^")
				.uploadDate(new Date()).user(userRepo.findByNickname("김장이")).location("대구광역시 남구").soldDate(new Date()).build();
		
		List<UploadFile> p1Imgs = new ArrayList<>();
		p1Imgs.add(UploadFile.builder().fileName("switch.jpg").storedFileName("switch.jpg").build());
		List<UploadFile> p2Imgs = new ArrayList<>();
		p2Imgs.add(UploadFile.builder().fileName("python.jpg").storedFileName("python.jpg").build());
		List<UploadFile> p3Imgs = new ArrayList<>();
		p3Imgs.add(UploadFile.builder().fileName("nin.jpg").storedFileName("nin.jpg").build());
		List<UploadFile> p4Imgs = new ArrayList<>();
		p4Imgs.add(UploadFile.builder().fileName("ring.jpg").storedFileName("ring.jpg").build());
		List<UploadFile> p5Imgs = new ArrayList<>();
		p5Imgs.add(UploadFile.builder().fileName("pants.jpg").storedFileName("pants.jpg").build());
		List<UploadFile> p6Imgs = new ArrayList<>();
		p6Imgs.add(UploadFile.builder().fileName("ball.jpg").storedFileName("ball.jpg").build());
		List<UploadFile> p7Imgs = new ArrayList<>();
		p7Imgs.add(UploadFile.builder().fileName("ddiyong.jpeg").storedFileName("ddiyong.jpeg").build());
		List<UploadFile> p8Imgs = new ArrayList<>();
		p8Imgs.add(UploadFile.builder().fileName("cac.jpg").storedFileName("cac.jpg").build());
		List<UploadFile> p9Imgs = new ArrayList<>();
		p9Imgs.add(UploadFile.builder().fileName("mask.jpg").storedFileName("mask.jpg").build());
		List<UploadFile> p10Imgs = new ArrayList<>();
		p10Imgs.add(UploadFile.builder().fileName("fkill.jpg").storedFileName("fkill.jpg").build());
		List<UploadFile> p11Imgs = new ArrayList<>();
		p11Imgs.add(UploadFile.builder().fileName("meat.jpg").storedFileName("meat.jpg").build());
		List<UploadFile> p12Imgs = new ArrayList<>();
		p12Imgs.add(UploadFile.builder().fileName("ssen.jpeg").storedFileName("ssen.jpeg").build());
		List<UploadFile> p13Imgs = new ArrayList<>();
		p13Imgs.add(UploadFile.builder().fileName("books.jpeg").storedFileName("books.jpeg").build());
		List<UploadFile> p14Imgs = new ArrayList<>();
		p14Imgs.add(UploadFile.builder().fileName("lotto.jpg").storedFileName("lotto.jpg").build());
		List<UploadFile> p15Imgs = new ArrayList<>();
		p15Imgs.add(UploadFile.builder().fileName("dog.jpeg").storedFileName("dog.jpeg").build());
		List<UploadFile> p16Imgs = new ArrayList<>();
		p16Imgs.add(UploadFile.builder().fileName("com.jpg").storedFileName("com.jpg").build());
		List<UploadFile> p17Imgs = new ArrayList<>();
		p17Imgs.add(UploadFile.builder().fileName("kimchi.jpeg").storedFileName("kimchi.jpeg").build());
		
		fileRepo.saveAll(p1Imgs);
		fileRepo.saveAll(p2Imgs);
		fileRepo.saveAll(p3Imgs);
		fileRepo.saveAll(p4Imgs);
		fileRepo.saveAll(p5Imgs);
		fileRepo.saveAll(p6Imgs);
		fileRepo.saveAll(p7Imgs);
		fileRepo.saveAll(p8Imgs);
		fileRepo.saveAll(p9Imgs);
		fileRepo.saveAll(p10Imgs);
		fileRepo.saveAll(p11Imgs);
		fileRepo.saveAll(p12Imgs);
		fileRepo.saveAll(p13Imgs);
		fileRepo.saveAll(p14Imgs);
		fileRepo.saveAll(p15Imgs);
		fileRepo.saveAll(p16Imgs);
		fileRepo.saveAll(p17Imgs);
		
		p1.setImages(p1Imgs);
		p2.setImages(p2Imgs);
		p3.setImages(p3Imgs);
		p4.setImages(p4Imgs);
		p5.setImages(p5Imgs);
		p6.setImages(p6Imgs);
		p7.setImages(p7Imgs);
		p8.setImages(p8Imgs);
		p9.setImages(p9Imgs);
		p10.setImages(p10Imgs);
		p11.setImages(p11Imgs);
		p12.setImages(p12Imgs);
		p13.setImages(p13Imgs);
		p14.setImages(p14Imgs);
		p15.setImages(p15Imgs);
		p16.setImages(p16Imgs);
		p17.setImages(p17Imgs);
		productRepo.save(p1);
		productRepo.save(p2);
		productRepo.save(p3);
		productRepo.save(p4);
		productRepo.save(p5);
		productRepo.save(p6);
		productRepo.save(p7);
		productRepo.save(p8);
		productRepo.save(p9);
		productRepo.save(p10);
		productRepo.save(p11);
		productRepo.save(p12);
		productRepo.save(p13);
		productRepo.save(p14);
		productRepo.save(p15);
		productRepo.save(p16);
		productRepo.save(p17);
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

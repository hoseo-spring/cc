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
		

		List<UploadFile> u1Imgs = new ArrayList<>();
		u1Imgs.add(UploadFile.builder().fileName("baby.jpg").storedFileName("baby.jpg").build());
		List<UploadFile> u2Imgs = new ArrayList<>();
		u2Imgs.add(UploadFile.builder().fileName("com.jpg").storedFileName("com.jpg").build());
		List<UploadFile> u3Imgs = new ArrayList<>();
		u3Imgs.add(UploadFile.builder().fileName("carrot.png").storedFileName("carrot.png").build());
		List<UploadFile> u4Imgs = new ArrayList<>();
		u4Imgs.add(UploadFile.builder().fileName("p1.jpg").storedFileName("p1.jpg").build());
		List<UploadFile> u5Imgs = new ArrayList<>();
		u5Imgs.add(UploadFile.builder().fileName("cjswo.jpg").storedFileName("cjswo.jpg").build());
		List<UploadFile> u6Imgs = new ArrayList<>();
		u6Imgs.add(UploadFile.builder().fileName("aoal.jpeg").storedFileName("aoal.jpeg").build());
		List<UploadFile> u7Imgs = new ArrayList<>();
		u7Imgs.add(UploadFile.builder().fileName("jammin.png").storedFileName("jammin.png").build());
		List<UploadFile> u8Imgs = new ArrayList<>();
		u8Imgs.add(UploadFile.builder().fileName("dog.jpg").storedFileName("dog.jpg").build());
		List<UploadFile> u9Imgs = new ArrayList<>();
		u9Imgs.add(UploadFile.builder().fileName("flower.jpg").storedFileName("flower.jpg").build());
		List<UploadFile> u10Imgs = new ArrayList<>();
		u10Imgs.add(UploadFile.builder().fileName("vkqmfm.jpg").storedFileName("vkqmfm.jpg").build());
		
		List<UploadFile> u11Imgs = new ArrayList<>();
		u11Imgs.add(UploadFile.builder().fileName("duck.jpg").storedFileName("duck.jpg").build());
		List<UploadFile> u12Imgs = new ArrayList<>();
		u12Imgs.add(UploadFile.builder().fileName("cat.jpeg").storedFileName("cat.jpeg").build());
		List<UploadFile> u13Imgs = new ArrayList<>();
		u13Imgs.add(UploadFile.builder().fileName("wow.jpg").storedFileName("wow.jpg").build());
		List<UploadFile> u14Imgs = new ArrayList<>();
		u14Imgs.add(UploadFile.builder().fileName("treasure.jpg").storedFileName("treasure.jpg").build());
		List<UploadFile> u15Imgs = new ArrayList<>();
		u15Imgs.add(UploadFile.builder().fileName("mermaid.png").storedFileName("mermaid.png").build());
		List<UploadFile> u16Imgs = new ArrayList<>();
		u16Imgs.add(UploadFile.builder().fileName("baldhair.jpg").storedFileName("baldhair.jpg").build());
		List<UploadFile> u17Imgs = new ArrayList<>();
		u17Imgs.add(UploadFile.builder().fileName("Qnfld.png").storedFileName("Qnfld.png").build());
		List<UploadFile> u18Imgs = new ArrayList<>();
		u18Imgs.add(UploadFile.builder().fileName("beam.jpeg").storedFileName("beam.jpeg").build());
		List<UploadFile> u19Imgs = new ArrayList<>();
		u19Imgs.add(UploadFile.builder().fileName("gang.png").storedFileName("gang.png").build());
		List<UploadFile> u20Imgs = new ArrayList<>();
		u20Imgs.add(UploadFile.builder().fileName("pepero.jpeg").storedFileName("pepero.jpeg").build());
		fileRepo.saveAll(u1Imgs);
		fileRepo.saveAll(u2Imgs);
		fileRepo.saveAll(u3Imgs);
		fileRepo.saveAll(u4Imgs);
		fileRepo.saveAll(u5Imgs);
		fileRepo.saveAll(u6Imgs);
		fileRepo.saveAll(u7Imgs);
		fileRepo.saveAll(u8Imgs);
		fileRepo.saveAll(u9Imgs);
		fileRepo.saveAll(u10Imgs);
		fileRepo.saveAll(u11Imgs);
		fileRepo.saveAll(u12Imgs);
		fileRepo.saveAll(u13Imgs);
		fileRepo.saveAll(u14Imgs);
		fileRepo.saveAll(u15Imgs);
		fileRepo.saveAll(u16Imgs);
		fileRepo.saveAll(u17Imgs);
		fileRepo.saveAll(u18Imgs);
		fileRepo.saveAll(u19Imgs);
		fileRepo.saveAll(u20Imgs);
		
		userService.createUser(User.builder().id("test").password("1234").nickname("테스트").registeredDate(new Date()).images(u3Imgs).build());
		userService.createUser(User.builder().id("asdf1234").password("789").nickname("닉네임").registeredDate(new Date()).images(u4Imgs).build());
		userService.createUser(User.builder().id("rlacjswo").password("12345").nickname("김천재").registeredDate(new Date()).images(u5Imgs).build());
		userService.createUser(User.builder().id("test2").password("1234").nickname("기매미").registeredDate(new Date()).images(u6Imgs).build());
		userService.createUser(User.builder().id("jammin").password("1234").nickname("잼민이").registeredDate(new Date()).images(u7Imgs).build());
		userService.createUser(User.builder().id("ajdajddl").password("1234").nickname("멍멍이").registeredDate(new Date()).images(u8Imgs).build());
		userService.createUser(User.builder().id("zjazjadl").password("1234").nickname("컴컴이").registeredDate(new Date()).images(u2Imgs).build());
		userService.createUser(User.builder().id("rlawkddl").password("1234").nickname("김장이").registeredDate(new Date()).images(u9Imgs).build());
		userService.createUser(User.builder().id("ahrlqkrauf").password("1234").nickname("모기박멸").registeredDate(new Date()).images(u10Imgs).build());
		userService.createUser(User.builder().id("qkrcjswo").password("1234").nickname("이천재").registeredDate(new Date()).images(u11Imgs).build());
		userService.createUser(User.builder().id("akqjqrhdiddl").password("1234").nickname("마법고양이").registeredDate(new Date()).images(u12Imgs).build());
		userService.createUser(User.builder().id("djacjdsksqkwl").password("1234").nickname("엄청난바지").registeredDate(new Date()).images(u13Imgs).build());
		userService.createUser(User.builder().id("qhanfrhqmffls").password("1234").nickname("보물고블린").registeredDate(new Date()).images(u14Imgs).build());
		userService.createUser(User.builder().id("dlsdjrhdwn").password("1234").nickname("인어공주").registeredDate(new Date()).images(u15Imgs).build());
		userService.createUser(User.builder().id("alsajfl").password("1234").nickname("민머리").registeredDate(new Date()).images(u16Imgs).build());
		userService.createUser(User.builder().id("Qnfldzmf").password("1234").nickname("뿌링클").registeredDate(new Date()).images(u17Imgs).build());
		userService.createUser(User.builder().id("dortusqla").password("1234").nickname("액션빔").registeredDate(new Date()).images(u18Imgs).build());
		userService.createUser(User.builder().id("rkdxorhd2").password("1234").nickname("강태공2").registeredDate(new Date()).images(u19Imgs).build());
		userService.createUser(User.builder().id("QOQOfh").password("1234").nickname("뺴뺴로").registeredDate(new Date()).images(u20Imgs).build());
		userService.createUser(User.builder().id("dkrlehowl").password("1234").nickname("아기돼지").registeredDate(new Date()).images(u1Imgs).build());
		
		
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
		Product p14 = Product.builder().name("복권 판매").category("기타").price(10000).description("수동5개고요 돼지꿈 꿨습니다")
				.uploadDate(new Date()).user(userRepo.findByNickname("김천재")).location("충청남도 계룡시").build();
		Product p15 = Product.builder().name("강아지").category("기타").price(1000000000).description("그냥 자랑하려고 올립니다. 감탄하고 가세요!")
				.uploadDate(new Date()).user(userRepo.findByNickname("멍멍이")).location("경기도 고양시").build();
		Product p16 = Product.builder().name("구형 컴퓨터긴한데 잘 돌아감").category("전자/가전").price(35000).description("지뢰찾기 풀옵가능, 묶혀놨다가 유물로 재판매 가능")
				.uploadDate(new Date()).user(userRepo.findByNickname("컴컴이")).location("경상북도 경산시").build();
		Product p17 = Product.builder().name("김장김치 무료 나눔~~").category("기타").price(100).description("이번에 본가 가서 김장한거 무료나눔해요~~~ ^,,,^")
				.uploadDate(new Date()).user(userRepo.findByNickname("김장이")).location("대구광역시 남구").soldDate(new Date()).build();
		Product p18 = Product.builder().name("제가 초3때 쓴 일기장입니다").category("완구/취미").price(10000).description("열심히 썼고요, 어제 읽어봤는데 짱 재밌음")
		.uploadDate(new Date()).user(userRepo.findByNickname("마법고양이")).location("대구광역시 북구").build();
		Product p19 = Product.builder().name("접히는 키보드").category("전자/가전").price(7000).description("신기해서 샀는데…. 쓸일이 없네요….\n제발 사주세요 ㅠㅠㅠㅠㅠ")
				.uploadDate(new Date()).user(userRepo.findByNickname("엄청난바지")).location("경기도 군포시").build();
		Product p20 = Product.builder().name("손수 뜨개질한 목도리!").category("의류").price(12000).description("3개월만의 결과랍니다~~ \n너무 따뜻하고 부드러운데 특별히 팔아요!")
				.uploadDate(new Date()).user(userRepo.findByNickname("보물고블린")).location("인천광역시 중구").build();
		Product p21 = Product.builder().name("물고기 분양해요").category("기타").price(20000).description("그저께 태어난 아기물고기예요~ 대략 20마리정도고요/n저희집 어항이 너무 좁아서 분양해요")
				.uploadDate(new Date()).user(userRepo.findByNickname("인어공주")).location("전라북도 전주시").build();
		Product p22 = Product.builder().name("미용실 쿠폰").category("기타").price(3000).description("이벤트 당첨됐는데 제가 대머리라서.. 미용실 갈 일이 없네요…")
				.uploadDate(new Date()).user(userRepo.findByNickname("민머리")).location("강원도 태백시").build();
		Product p23 = Product.builder().name("치킨 쿠폰 팝니다").category("기타").price(7000).description("2번만 더 시켜드시면 후라이드 한마리 드실수 있어요!")
				.uploadDate(new Date()).user(userRepo.findByNickname("뿌링클")).location("경상북도 문경시").build();
		Product p24 = Product.builder().name("휴대용 빔프로젝터").category("전자/가전").price(20000).description("성능 정말 좋아요~\n근데 생각보다 안써서 다른분이 알차게 써주셨음 해요!")
				.uploadDate(new Date()).user(userRepo.findByNickname("액션빔")).location("충청남도 보령시").build();
		Product p25 = Product.builder().name("낚싯대").category("완구/취미").price(60000).description("이젠 낚시보다 골프가 재밌더라구요!! 물고기 엄청 잘 잡히는겁니다!!!")
				.uploadDate(new Date()).user(userRepo.findByNickname("강태공2")).location("울산 울주군").build();
		Product p26 = Product.builder().name("직접 만든 빼빼로").category("기타").price(1111).description("빼빼로 못받을거 같은사람 있냐? ㅋㅋ직접 정성을 담아 만들었으니 받고싶으면 톡하던지말던지 ㅋㅋㅋ")
				.uploadDate(new Date()).user(userRepo.findByNickname("뺴뺴로")).location("서울특별시 동작구").build();
		Product p27 = Product.builder().name("노트북 팔아요").category("전자/가전").price(400000).description("좀 느린편이고 살짝 고장나고 기스좀 있어요.\n그래도 이정도면 싼편이죠?")
				.uploadDate(new Date()).user(userRepo.findByNickname("컴컴이")).location("경상북도 경산시").build();
		Product p28 = Product.builder().name("아기 뽁뽁이 신발").category("의류").price(10000).description("아기가 안신으려해서~ 팔아용\n몇번 안신었고 귀여워요~~ 사이즈는 쪼끄매용~!")
				.uploadDate(new Date()).user(userRepo.findByNickname("아기돼지")).location("경상남도 진해시").build();

		
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
		List<UploadFile> p18Imgs = new ArrayList<>();
		p18Imgs.add(UploadFile.builder().fileName("diary.jpg").storedFileName("diary.jpg").build());
		List<UploadFile> p19Imgs = new ArrayList<>();
		p19Imgs.add(UploadFile.builder().fileName("key.jpg").storedFileName("key.jpg").build());
		List<UploadFile> p20Imgs = new ArrayList<>();
		p20Imgs.add(UploadFile.builder().fileName("mok.jpg").storedFileName("mok.jpg").build());
		List<UploadFile> p21Imgs = new ArrayList<>();
		p21Imgs.add(UploadFile.builder().fileName("fish.png").storedFileName("fish.png").build());
		List<UploadFile> p22Imgs = new ArrayList<>();
		p22Imgs.add(UploadFile.builder().fileName("coupon.jpg").storedFileName("coupon.jpg").build());
		List<UploadFile> p23Imgs = new ArrayList<>();
		p23Imgs.add(UploadFile.builder().fileName("chicken.jpg").storedFileName("chicken.jpg").build());
		List<UploadFile> p24Imgs = new ArrayList<>();
		p24Imgs.add(UploadFile.builder().fileName("beam.jpeg").storedFileName("beam.jpeg").build());
		p24Imgs.add(UploadFile.builder().fileName("beam2.jpg").storedFileName("beam2.jpg").build());
		List<UploadFile> p25Imgs = new ArrayList<>();
		p25Imgs.add(UploadFile.builder().fileName("rod.jpg").storedFileName("rod.jpg").build());
		p25Imgs.add(UploadFile.builder().fileName("rod2.jpg").storedFileName("rod2.jpg").build());
		List<UploadFile> p26Imgs = new ArrayList<>();
		p26Imgs.add(UploadFile.builder().fileName("pepero1.jpg").storedFileName("pepero1.jpg").build());
		p26Imgs.add(UploadFile.builder().fileName("pepero2.jpg").storedFileName("pepero2.jpg").build());
		p26Imgs.add(UploadFile.builder().fileName("pepero3.jpg").storedFileName("pepero3.jpg").build());
		List<UploadFile> p27Imgs = new ArrayList<>();
		p27Imgs.add(UploadFile.builder().fileName("note.jpg").storedFileName("note.jpg").build());
		List<UploadFile> p28Imgs = new ArrayList<>();
		p28Imgs.add(UploadFile.builder().fileName("shoes.jpeg").storedFileName("shoes.jpeg").build());
		
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
		fileRepo.saveAll(p18Imgs);
		fileRepo.saveAll(p19Imgs);
		fileRepo.saveAll(p20Imgs);
		fileRepo.saveAll(p21Imgs);
		fileRepo.saveAll(p22Imgs);
		fileRepo.saveAll(p23Imgs);
		fileRepo.saveAll(p24Imgs);
		fileRepo.saveAll(p25Imgs);
		fileRepo.saveAll(p26Imgs);
		fileRepo.saveAll(p27Imgs);
		fileRepo.saveAll(p28Imgs);
		
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
		p18.setImages(p18Imgs);
		p19.setImages(p19Imgs);
		p20.setImages(p20Imgs);
		p21.setImages(p21Imgs);
		p22.setImages(p22Imgs);
		p23.setImages(p23Imgs);
		p24.setImages(p24Imgs);
		p25.setImages(p25Imgs);
		p26.setImages(p26Imgs);
		p27.setImages(p27Imgs);
		p28.setImages(p28Imgs);


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
		productRepo.save(p18);
		productRepo.save(p19);
		productRepo.save(p20);
		productRepo.save(p21);
		productRepo.save(p22);
		productRepo.save(p23);
		productRepo.save(p24);
		productRepo.save(p25);
		productRepo.save(p26);
		productRepo.save(p27);
		productRepo.save(p28);

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

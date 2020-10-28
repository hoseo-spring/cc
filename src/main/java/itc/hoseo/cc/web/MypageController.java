package itc.hoseo.cc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import itc.hoseo.cc.domain.User;
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
	UserService userservice;
	
	@RequestMapping(path = "/mypage", method = {RequestMethod.GET,RequestMethod.POST}) 
	public String mypageGet(ModelMap mm) {
		User user = userContext.getCurrentUser();
		productRepo.findByUserId(user.getId(), PageRequest.of(0, 5));
		mm.put("user", user);
		mm.put("product", productRepo.findByUserId(user.getId(), PageRequest.of(0, 5)));
		return "mypage";
	}

	@RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)  
	public String editPost(Model mm, String id, User user) {
		userservice.findById(id);
		return "mypage";
	}
	
	
	/*
	 * public String updateUser(Model mm,String id, String nickname, String password) {
		User user = userRepo.findById(id).get();
		userRepo.save(User.builder().nickname(nickname).password(password).build());
		return "mypage";
		}
	 * 
	 */
}

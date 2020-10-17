package itc.hoseo.cc.web;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import itc.hoseo.cc.domain.User;
import itc.hoseo.cc.repository.UserRepository;
import itc.hoseo.cc.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String registerPost(ModelMap mm, String id, String password, String repassword, String nickname) {
		User user = User.builder().id(id).password(password).nickname(nickname).registeredDate(new Date()).build();
		userService.createUser(user);
		
		return "redirect:/";
	}

	@RequestMapping(path = "/registerError", method = RequestMethod.GET)
	public String registerErrorGet(ModelMap mm) {
		return "registerError";
	}
}

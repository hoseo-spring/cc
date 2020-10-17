package itc.hoseo.cc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import itc.hoseo.cc.service.SpringSecurityUserContext;

@Controller
public class MypageController {

	@Autowired
	SpringSecurityUserContext userContext;
	
	@RequestMapping(path = "/mypage", method = RequestMethod.GET) 
	public String mypageGet(ModelMap mm) {
		mm.put("user", userContext.getCurrentUser());
		return "mypage";
	}
	
}

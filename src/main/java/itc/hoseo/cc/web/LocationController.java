package itc.hoseo.cc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import itc.hoseo.cc.domain.User;
import itc.hoseo.cc.service.LocationService;
import itc.hoseo.cc.service.SpringSecurityUserContext;

@Controller
public class LocationController {

	@Autowired
	SpringSecurityUserContext userContext;
	
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(path = "/location", method = RequestMethod.POST)
	public String saveLocation(ModelMap mm, String state, String city, String currentLocation) {
		User user = userContext.getCurrentUser();
		if(locationService.saveLocation(state, city, user, currentLocation)) { 
			return "redirect:"; // 지역 인증 성공 페이지
		} else {
			return "redirect:"; // 지역 인증 실패 페이지
		}
	}
	
}

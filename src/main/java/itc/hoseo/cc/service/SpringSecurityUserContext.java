package itc.hoseo.cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import itc.hoseo.cc.domain.User;
import itc.hoseo.cc.repository.UserRepository;

@Component
public class SpringSecurityUserContext  implements UserContext{
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User getCurrentUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();
		if(auth == null) throw new RuntimeException("로그인 정보가 필요합니다.");
		return userRepo.findById(auth.getName()).get();
	}

	@Override
	public void setCurrentUser(User user) {
		throw new UnsupportedOperationException("아직 구현중입니다.");
	}

}

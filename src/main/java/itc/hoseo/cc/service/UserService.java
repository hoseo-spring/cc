package itc.hoseo.cc.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import itc.hoseo.cc.domain.User;
import itc.hoseo.cc.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserDetailsManager userDetailsManager;
	
	
	public boolean createUser(User user) {
		List<GrantedAuthority> authList = AuthorityUtils.createAuthorityList("ROLE_USER");
		UserDetails userDetails = 
				new org.springframework.security.core.userdetails.User(user.getId(),
						"{noop}" + user.getPassword(), authList);
		userDetailsManager.createUser(userDetails);
		userRepo.save(user);
		return true;
	}
	
	public Optional<User> findById(String id){
		Optional<User> user = userRepo.findById(id);
		return user;
	}
	
	@Transactional
    public void updateUser(User user, String id) {
		Optional<User> u = userRepo.findById(id);
		if (u.isPresent()) {
			u.get().setNickname(u.get().getNickname());
			u.get().setPassword(u.get().getPassword());
			userRepo.save(user);
		}
	}
}

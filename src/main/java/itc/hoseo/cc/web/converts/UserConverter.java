package itc.hoseo.cc.web.converts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import itc.hoseo.cc.domain.User;
import itc.hoseo.cc.repository.UserRepository;

@Component
public class UserConverter implements Converter<String, User> {
	
	@Autowired
	private UserRepository repo;

	@Override
	public User convert(String value) {
		return repo.findById(value).get();
	}

}

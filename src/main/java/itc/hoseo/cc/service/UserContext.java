package itc.hoseo.cc.service;

import itc.hoseo.cc.domain.User;

public interface UserContext {
	User getCurrentUser();
	void setCurrentUser(User user);
}

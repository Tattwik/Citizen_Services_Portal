package tech.csm.service;

import tech.csm.domain.UserMaster;

public interface UserService {

	UserMaster saveUser(UserMaster user);

	String validateUser(String userName, String password);

}

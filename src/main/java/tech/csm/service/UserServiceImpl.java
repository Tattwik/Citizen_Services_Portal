package tech.csm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.domain.UserMaster;
import tech.csm.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserMaster saveUser(UserMaster user) {
		return userRepo.save(user);
	}

	@Override
	public String validateUser(String userName, String password) {
		String res = "okay";
		List<UserMaster> userList = userRepo.findUser(userName);
        System.out.println(userList);
        if (userList.size() == 0) {
        	res = "User name or password is incorrect";
        } else {
        	System.out.println(userList.get(0).getPassword());
        	if (!userList.get(0).getPassword().equals(password)) {
        		res = "Password is incorrect";
        	}
        }
        if (res.equals("User name or password is incorrect") || res.equals("Password is incorrect")) {
        	return res;
        } else {
        	res = userList.get(0).getUserRole();
        	return res;
        }
	}

}

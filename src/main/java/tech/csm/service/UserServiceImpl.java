package tech.csm.service;

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

}

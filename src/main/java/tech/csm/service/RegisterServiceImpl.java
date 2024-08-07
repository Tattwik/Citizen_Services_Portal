package tech.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.domain.UserRegistration;
import tech.csm.repository.RegisterRepo;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterRepo registerRepo;
	
	@Override
	public UserRegistration registerUser(UserRegistration userRegistration) {
		return registerRepo.save(userRegistration);
	}

}

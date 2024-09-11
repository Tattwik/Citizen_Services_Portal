package tech.csm.service;

import tech.csm.domain.UserRegistration;

public interface RegisterService {

	UserRegistration registerUser(UserRegistration userRegistration);

	UserRegistration findUser(String attribute);

}

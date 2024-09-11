package tech.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.domain.BirthCertificateApplicationMaster;
import tech.csm.repository.BirthCARepo;

@Service
public class BirthCAServiceImpl implements BirthCAService {

	@Autowired
	private BirthCARepo bCARepo;
	
	@Override
	public BirthCertificateApplicationMaster saveBC(BirthCertificateApplicationMaster birth) {
		return bCARepo.save(birth);
	}

}

package tech.csm.service;

import java.util.List;

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

	@Override
	public List<BirthCertificateApplicationMaster> getAllByrId(Integer rId) {
		return bCARepo.getAllByrId(rId);
	}

	@Override
	public List<BirthCertificateApplicationMaster> getAll() {
		return bCARepo.getAllPending();
	}

	@Override
	public int approveBirth(int bId, String userName) {
		return bCARepo.approveBirth(bId,userName);
	}

	@Override
	public BirthCertificateApplicationMaster getData(int bId) {
		return bCARepo.getData(bId);
	}

	@Override
	public int rejectBirth(int bId) {
		return bCARepo.rejectBirth(bId);
	}

}

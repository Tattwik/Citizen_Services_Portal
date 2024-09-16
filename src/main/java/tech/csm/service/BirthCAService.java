package tech.csm.service;

import java.util.List;

import tech.csm.domain.BirthCertificateApplicationMaster;

public interface BirthCAService {

	BirthCertificateApplicationMaster saveBC(BirthCertificateApplicationMaster birth);

	List<BirthCertificateApplicationMaster> getAllByrId(Integer rId);

	List<BirthCertificateApplicationMaster> getAll();

	int approveBirth(int bId, String authority);

	BirthCertificateApplicationMaster getData(int bId);

	int rejectBirth(int bId);

}

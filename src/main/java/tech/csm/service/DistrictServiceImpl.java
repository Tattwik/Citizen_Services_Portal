package tech.csm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.domain.DistrictMaster;
import tech.csm.repository.DistrictRepo;

@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private DistrictRepo districtRepo;
	
	@Override
	public List<DistrictMaster> getDistrictByStateId(Integer stateId) {
		return districtRepo.findDistrictByStateId(stateId);
	}

}

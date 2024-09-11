package tech.csm.service;

import java.util.List;

import tech.csm.domain.DistrictMaster;

public interface DistrictService {

	List<DistrictMaster> getDistrictByStateId(Integer stateId);

}

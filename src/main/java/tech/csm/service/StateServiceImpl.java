package tech.csm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.domain.StateMaster;
import tech.csm.repository.StateRepo;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateRepo stateRepo;
	
	@Override
	public List<StateMaster> getAllStates() {
		return stateRepo.findAll();
	}

}

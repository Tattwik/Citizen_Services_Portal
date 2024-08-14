package tech.csm.service;

import org.springframework.data.domain.Pageable;

public interface PracticeService {

	String getData(String stateName, Integer pageSize, Integer pageNumber, Pageable pageable);

}

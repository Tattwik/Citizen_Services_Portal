package tech.csm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.csm.domain.Practice;

@Repository
public interface PracticeRepo extends JpaRepository<Practice, Integer> {

	@Query("FROM Practice p WHERE p.state=:stateName")
	Page<Practice> getDataByName(Pageable pageable, @Param("stateName") String stateName);

}

package tech.csm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.csm.domain.DistrictMaster;

@Repository
public interface DistrictRepo extends JpaRepository<DistrictMaster, Integer> {

	@Query(value="SELECT * FROM district WHERE s_id=:stateId", nativeQuery = true)
	List<DistrictMaster> findDistrictByStateId(@Param("stateId") Integer stateId);

}

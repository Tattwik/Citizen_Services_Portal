package tech.csm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tech.csm.domain.BirthCertificateApplicationMaster;

@Repository
public interface BirthCARepo extends JpaRepository<BirthCertificateApplicationMaster, Integer> {

	@Query(value = "SELECT * FROM birth_certificate_application_master WHERE r_id=:rId", nativeQuery = true)
	List<BirthCertificateApplicationMaster> getAllByrId(@Param("rId") Integer rId);

	@Query(value = "SELECT * FROM birth_certificate_application_master WHERE status='PENDING'", nativeQuery = true)
	List<BirthCertificateApplicationMaster> getAllPending();

	@Modifying
	@Transactional
	@Query(value = "UPDATE birth_certificate_application_master SET status='APPROVED' , approved_by=:userName WHERE b_id=:bId", nativeQuery = true)
	int approveBirth(@Param("bId") Integer bId, @Param("userName") String userName);

	@Query(value="SELECT * FROM birth_certificate_application_master WHERE b_id=:bId", nativeQuery = true)
	BirthCertificateApplicationMaster getData(@Param("bId") Integer bId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE birth_certificate_application_master SET status='REJECTED'  WHERE b_id=:bId", nativeQuery = true)
	int rejectBirth(@Param("bId") Integer bId);

}

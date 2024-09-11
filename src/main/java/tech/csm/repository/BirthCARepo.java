package tech.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.csm.domain.BirthCertificateApplicationMaster;

@Repository
public interface BirthCARepo extends JpaRepository<BirthCertificateApplicationMaster, Integer> {

}

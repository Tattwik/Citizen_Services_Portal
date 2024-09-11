package tech.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.csm.domain.StateMaster;

@Repository
public interface StateRepo extends JpaRepository<StateMaster, Integer> {

}

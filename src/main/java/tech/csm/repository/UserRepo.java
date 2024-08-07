package tech.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.csm.domain.UserMaster;

@Repository
public interface UserRepo extends JpaRepository<UserMaster, Integer> {

}

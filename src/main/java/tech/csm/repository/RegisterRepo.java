package tech.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.csm.domain.UserRegistration;

@Repository
public interface RegisterRepo extends JpaRepository<UserRegistration, Integer> {

}

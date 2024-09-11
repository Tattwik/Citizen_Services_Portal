package tech.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.csm.domain.UserRegistration;

@Repository
public interface RegisterRepo extends JpaRepository<UserRegistration, Integer> {

	@Query(value="SELECT * FROM user_registration WHERE user_name=:name", nativeQuery = true)
	UserRegistration findUser(@Param("name") String name);

}

package tech.csm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.csm.domain.UserMaster;

@Repository
public interface UserRepo extends JpaRepository<UserMaster, Integer> {

	@Query(value = "SELECT u_id, user_name, password, user_role FROM user_master WHERE user_name = :userName", nativeQuery = true)
	List<UserMaster> findUser(@Param("userName") String userName);


}

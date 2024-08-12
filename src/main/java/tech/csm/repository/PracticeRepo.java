package tech.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.csm.domain.Practice;

@Repository
public interface PracticeRepo extends JpaRepository<Practice, Integer> {

}

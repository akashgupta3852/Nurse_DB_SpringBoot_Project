package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Nurse;

@Repository // it is optional in springboot
public interface NurseRepository extends CrudRepository<Nurse, Integer> {
	// @Query is better than @NamedQuery annotation
	@Query("select n from Nurse n where n.email = :email")
	List<Nurse> findByEmail(String email);

	@Query("select n from Nurse n where lower(n.lastName) = :lastName")
	List<Nurse> findByLastName(String lastName);
}

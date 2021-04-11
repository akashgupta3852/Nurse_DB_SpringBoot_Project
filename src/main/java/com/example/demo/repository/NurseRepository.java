package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Nurse;

public interface NurseRepository extends CrudRepository<Nurse, Integer> {
	List<Nurse> findByEmail(String email);

	List<Nurse> findByLastName(String lastName);
}

package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Nurse;

public interface NurseRepository extends CrudRepository<Nurse, Integer> {

}

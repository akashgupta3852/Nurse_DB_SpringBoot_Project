package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.NurseAddress;

import lombok.Data;

public @Data class NurseDTO {
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private double salary;
	private String email;
	private NurseAddress nurseAddress;
	private List<String> depList;

}
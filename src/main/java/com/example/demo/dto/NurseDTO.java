package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.NurseAddress;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class NurseDTO {
	@NotNull(message = "FirstName should not null")
	private String firstName;

	@NotNull(message = "LastName should not null")
	private String lastName;

	@Min(value = 30, message = "Age should not be less than 30")
	@Max(value = 35, message = "Age should not be greater than 35")
	private int age;

	@Min(value = 1, message = "Salary should be defined in the request body object")
	@Max(value = 90000, message = "Salary should not be greater than 90000")
	private double salary;

	private String email;
	private NurseAddress nurseAddress;

	@NotNull(message = "DepList should not null")
	private List<String> depList;
}
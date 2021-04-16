package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.NurseAddress;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class NurseDTO {
	private int id;
	private String firstName;
	private String lastName;

	@Min(value = 30, message = "Age should not be less than 18")
	@Max(value = 35, message = "Age should not be greater than 150")
	private int age;

	@NotBlank(message = "salary should not be empty")
	private double salary;

	private String email;
	private NurseAddress nurseAddress;

	@NotNull(message = "depList should not be null")
	private List<String> depList;

}
package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.NurseAddress;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class NurseDTO {
	private static final String NAME_PATTERN="^[A-Z][a-z]{2,}";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([+_.-][a-zA-Z0-9]+)*[@][a-zA-Z0-9]+[.][a-zA-Z]{2,4}$";

	@Pattern(regexp = NAME_PATTERN, message = "FirstName is invalid")
	@NotNull(message = "FirstName should not null")
	private String firstName;

	@Pattern(regexp = NAME_PATTERN, message = "LastName is invalid")
	@NotNull(message = "LastName should not null")
	private String lastName;

	@Min(value = 30, message = "Age should not be less than 30")
	@Max(value = 35, message = "Age should not be greater than 35")
	private int age;

	@Pattern(regexp = "Male|Female", message = "Gender needs to be Male or Female")
	private String gender;

	@JsonFormat(pattern = "dd MMM yyyy")
	@NotNull(message = "StartDate should not be empty")
	@PastOrPresent(message = "StartdDate should not be future date")
	private LocalDate startDate;

	@Min(value = 1, message = "Salary should be defined in the request body object")
	@Max(value = 90000, message = "Salary should not be greater than 90000")
	private double salary;

	@Pattern(regexp = EMAIL_PATTERN, message = "Email is invalid")
	private String email;
	
	private NurseAddress nurseAddress;

	@NotNull(message = "DepList should not null")
	private List<String> depList;
}
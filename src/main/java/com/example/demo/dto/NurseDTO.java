package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.NurseAddress;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

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
	
	@JsonFormat(pattern = "dd MMM yyyy")
	@NotNull(message = "StartDate should not be empty")
	@PastOrPresent(message = "StartdDate should not be future date")
	public LocalDate startDate;

	@Min(value = 1, message = "Salary should be defined in the request body object")
	@Max(value = 90000, message = "Salary should not be greater than 90000")
	private double salary;

	private String email;
	private NurseAddress nurseAddress;

	@NotNull(message = "DepList should not null")
	private List<String> depList;
}
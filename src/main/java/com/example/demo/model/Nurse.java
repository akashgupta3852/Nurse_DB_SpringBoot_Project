package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@ApiModel(description = "Details about the nurse")
public class Nurse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrementation..hibernate will do this
	@ApiModelProperty(notes = "The unique id of the nurse")
	private int id;
	@ApiModelProperty(notes = "The nurse's firstName")
	private String firstName;
	@ApiModelProperty(notes = "The nurse's lastName")
	private String lastName;
	@ApiModelProperty(notes = "The nurse's age")
	private int age;
	@ApiModelProperty(notes = "The nurse's salary")
	private double salary;
	@ApiModelProperty(notes = "The nurse's email")
	private String email;

	public void updateNurseData(int nurseId, Nurse nurse) {
		this.id = nurseId;
		this.age = nurse.age;
		this.salary = nurse.salary;
		this.firstName = nurse.firstName;
		this.lastName = nurse.lastName;
	}
}

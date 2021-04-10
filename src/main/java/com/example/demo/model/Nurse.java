package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "Details about the nurse")
public class Nurse {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrementation..hibernate will do this
	@ApiModelProperty(notes = "The unique id of the nurse")
	private int id;
	@ApiModelProperty(notes = "The nurse's name")
	private String name;
	@ApiModelProperty(notes = "The nurse's age")
	private int age;
	@ApiModelProperty(notes = "The nurse's salary")
	private double salary;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void updateNurseData(int nurseId, Nurse nurse) {
		this.id = nurseId;
		this.age = nurse.age;
		this.salary = nurse.salary;
		this.name = nurse.name;
	}
}

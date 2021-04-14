package com.example.demo.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@ApiModel(description = "Details about the nurse")
//@NamedQuery(name = "Nurse.findByEmail", query = "select n from Nurse n where n.email = ?1")
//lastName (it should be same as member variable name, otherwise it will throw exception)
//@NamedQuery(name = "Nurse.findByLastName", query = "select n from Nurse n where lower(n.lastName) = ?1")
@SecondaryTable(name = "address", pkJoinColumns = @PrimaryKeyJoinColumn(name = "nurse_id"))
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

	@ApiModelProperty(notes = "The nurse's address")
	@Embedded
	private NurseAddress nurseAddress;

	public void updateNurseData(int nurseId, Nurse nurse) {
		this.id = nurseId;
		this.age = nurse.age;
		this.salary = nurse.salary;
		this.firstName = nurse.firstName;
		this.lastName = nurse.lastName;
		this.email = nurse.email;
		this.nurseAddress = nurse.nurseAddress;
	}
}

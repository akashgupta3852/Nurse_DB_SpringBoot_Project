package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

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
@Table(name="nurse_data")
public class Nurse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrementation..hibernate will do this
	@ApiModelProperty(notes = "The unique id of the nurse")
	@Column(name = "Nurse_Id")
	private int id;

	@Column(name = "Nurse_First_Name", length = 20, nullable=false)
	@ApiModelProperty(notes = "The nurse's firstName")
	private String firstName;

	@Column(name = "Nurse_Last_Name", length = 20, nullable=false)
	@ApiModelProperty(notes = "The nurse's lastName")
	private String lastName;

	@Column(name = "Nurse_Age", length = 2)
	@ApiModelProperty(notes = "The nurse's age")
	private int age;

	@Column(name = "Nurse_Salary", length = 5)
	@ApiModelProperty(notes = "The nurse's salary")
	private double salary;

	@Column(name = "Nurse_Email_Id", length = 40, nullable=false)
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

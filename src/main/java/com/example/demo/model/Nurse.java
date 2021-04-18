package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

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
import javax.validation.constraints.Pattern;

import com.example.demo.dto.NurseDTO;

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
@Table(name = "nurse_data")
public class Nurse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrementation..hibernate will do this
	@ApiModelProperty(notes = "The unique id of the nurse")
	@Column(name = "Nurse_Id")
	private int id;

	@Column(name = "Nurse_First_Name", length = 20, nullable = false)
	@Pattern(regexp = "[A-Z][a-zA-Z\\s]{2,}")
	@ApiModelProperty(notes = "The nurse's firstName")
	private String firstName;

	@Column(name = "Nurse_Last_Name", length = 20, nullable = false)
	@Pattern(regexp = "[A-Z][a-zA-Z\\s]{2,}")
	@ApiModelProperty(notes = "The nurse's lastName")
	private String lastName;

	@Column(name = "Nurse_Age", length = 2)
	@ApiModelProperty(notes = "The nurse's age")
	private int age;

	@Column(name = "Nurse_Gender", length = 6)
	@Pattern(regexp = "Male|Female")
	@ApiModelProperty(notes = "The nurse's gender")
	private String gender;

	@Column(name = "Nurse_Salary", length = 5)
	@ApiModelProperty(notes = "The nurse's salary")
	private double salary;

	@Column(name = "Nurse_Email_Id", length = 40, nullable = false)
	@ApiModelProperty(notes = "The nurse's email")
	private String email;

	@Column(name = "Nurse_Start_Date")
	@ApiModelProperty(notes = "The nurse's start date")
	private LocalDate startDate;

	@ApiModelProperty(notes = "The nurse's address")
	@Embedded
	private NurseAddress nurseAddress;

	@Column(name = "Nurse_Departments", length = 60)
	@ApiModelProperty(notes = "The departments' name in which nurse is working")
	private String departments;

	public Nurse() {
	}

	public Nurse(NurseDTO nurseDTO) {
		this.age = nurseDTO.getAge();
		this.salary = nurseDTO.getSalary();
		this.firstName = nurseDTO.getFirstName();
		this.lastName = nurseDTO.getLastName();
		this.email = nurseDTO.getEmail();
		this.nurseAddress = nurseDTO.getNurseAddress();
		this.departments = this.covertDepListToString(nurseDTO.getDepList());
		this.startDate = nurseDTO.getStartDate();
		this.gender = nurseDTO.getGender();
	}

	public Nurse updateNurseData(int nurseId, NurseDTO nurseDTO) {
		Nurse nurse = new Nurse(nurseDTO);
		nurse.id = nurseId;
		return nurse;
	}

	public String covertDepListToString(List<String> depList) {
		String str = "";
		int len = depList.size();
		for (int index = 0; index < len; index++) {
			str = str + depList.get(index);
			if (index < len - 1)
				str = str + ", ";
		}
		return str;
	}
}

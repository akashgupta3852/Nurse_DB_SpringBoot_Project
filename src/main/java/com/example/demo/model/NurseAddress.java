package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class NurseAddress implements Serializable {
	@Column(name = "Nurse_Address", length = 20)
	@ApiModelProperty(notes = "The nurse's address")
	private String address;

	@Column(name = "Nurse_City_Name", length = 20)
	@ApiModelProperty(notes = "The nurse's cityName")
	private String cityName;

	@Column(name = "Nurse_State_Name", length = 20)
	@ApiModelProperty(notes = "The nurse's stateName")
	private String stateName;

}

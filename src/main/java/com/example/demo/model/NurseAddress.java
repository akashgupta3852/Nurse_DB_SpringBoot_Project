package com.example.demo.model;

import javax.persistence.Embeddable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class NurseAddress {
	@ApiModelProperty(notes = "The nurse's address")
	private String address;

	@ApiModelProperty(notes = "The nurse's cityName")
	private String cityName;

	@ApiModelProperty(notes = "The nurse's stateName")
	private String stateName;

}

package com.example.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PincodeResponse implements Serializable {

	private static final long serialVersionUID = -3240464955481520412L;

	private Long id;
	private String pincode;
	private String areaName;
	private String cityName;
}

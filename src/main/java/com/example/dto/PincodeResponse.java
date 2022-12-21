package com.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PincodeResponse {

    private Long id;
    private String pincode;
    private String areaName;
    private String cityName;
}

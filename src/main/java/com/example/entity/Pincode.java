package com.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pincode_details")
public class Pincode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pincode;
    private String areaName;
    private String cityName;

}

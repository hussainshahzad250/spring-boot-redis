package com.example.service;

import com.example.dto.PincodeRequest;
import com.example.dto.PincodeResponse;

import java.util.List;

public interface PincodeService {
    PincodeResponse addPincode(PincodeRequest pincodeRequest) throws Exception;

    PincodeResponse getPincode(String pincode) throws Exception;

    List<PincodeResponse> getPincodeAndCity(String pincode, String city);
}

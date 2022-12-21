package com.example.service;

import com.example.dto.PincodeRequest;
import com.example.dto.PincodeResponse;

public interface PincodeService {
    PincodeResponse addPincode(PincodeRequest pincodeRequest) throws Exception;

    PincodeResponse getPincode(Long pincode) throws Exception;
}

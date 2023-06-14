package com.example.assembler;

import com.example.dto.PincodeRequest;
import com.example.dto.PincodeResponse;
import com.example.entity.Pincode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PincodeAssembler {

    public Pincode dtoToEntity(PincodeRequest pincodeRequest) {
        Pincode pincode = new Pincode();
        pincode.setPincode(pincodeRequest.getPincode());
        pincode.setAreaName(pincodeRequest.getAreaName());
        pincode.setCityName(pincodeRequest.getCityName());
        return pincode;
    }

    public PincodeResponse entityToDto(Pincode pincode) {
        PincodeResponse response = new PincodeResponse();
        response.setId(pincode.getId());
        response.setPincode(pincode.getPincode());
        response.setAreaName(pincode.getAreaName());
        response.setCityName(pincode.getCityName());
        return response;
    }

    public List<PincodeResponse> entityToDtoList(List<Pincode> requests) {
        return requests.parallelStream().map(this::entityToDto).collect(Collectors.toList());
    }
}

package com.example.controller;

import com.example.dto.PincodeRequest;
import com.example.dto.PincodeResponse;
import com.example.service.PincodeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/pincode")
public class PincodeController {

    private final PincodeService pincodeService;

    @PostMapping
    public ResponseEntity<Object> savePincode(@RequestBody PincodeRequest pincodeRequest) throws Exception {
        pincodeService.addPincode(pincodeRequest);
        return new ResponseEntity<>("Pincode added successfully", HttpStatus.OK);
    }

    @Cacheable(key = "#id", value = "pincode")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPincode(@PathVariable("id") Long pincode) throws Exception {
        PincodeResponse response = pincodeService.getPincode(pincode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


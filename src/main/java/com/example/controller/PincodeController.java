package com.example.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PincodeRequest;
import com.example.dto.PincodeResponse;
import com.example.service.PincodeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/pincode")
public class PincodeController {

	private final PincodeService pincodeService;

	@PostMapping
	public ResponseEntity<Object> savePincode(@RequestBody PincodeRequest pincodeRequest) throws Exception {
		PincodeResponse addPincode = pincodeService.addPincode(pincodeRequest);
		return new ResponseEntity<>(addPincode, HttpStatus.OK);
	}

	@Cacheable(key = "#pincode", value = "pincode")
	@GetMapping("/{pincode}")
	public PincodeResponse getPincode(@PathVariable("pincode") String pincode) throws Exception {
		return pincodeService.getPincode(pincode);
	}

}

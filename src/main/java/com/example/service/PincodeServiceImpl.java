package com.example.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.assembler.PincodeAssembler;
import com.example.constant.Constant;
import com.example.dto.PincodeRequest;
import com.example.dto.PincodeResponse;
import com.example.entity.Pincode;
import com.example.exception.BadRequestException;
import com.example.repository.PincodeRepository;

@Service
public class PincodeServiceImpl implements PincodeService, Constant {

	@Autowired
	private PincodeAssembler pincodeAssembler;

	@Autowired
	private PincodeRepository pincodeRepository;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private HashOperations<String, String, String> operations;

	@PostConstruct
	private void initializeHashOperation() {
		operations = redisTemplate.opsForHash();
	}

	@Override
	public PincodeResponse addPincode(PincodeRequest pincodeRequest) throws Exception {
		Optional<Pincode> optional = pincodeRepository.findByPincode(pincodeRequest.getPincode());
		if (optional.isPresent())
			throw new BadRequestException("pincode already exist", HttpStatus.BAD_REQUEST);
		Pincode pincode = pincodeAssembler.dtoToEntity(pincodeRequest);
		pincodeRepository.save(pincode);
		PincodeResponse response = pincodeAssembler.entityToDto(pincode);
		operations.put(PINCODE, response.getPincode(), response.getPincode());
		return response;
	}

	@Override
	public PincodeResponse getPincode(String pinCode) throws Exception {
		Optional<Pincode> optional = pincodeRepository.findByPincode(pinCode);
		if (optional.isPresent()) {
			Pincode pincode = optional.get();
			PincodeResponse response = pincodeAssembler.entityToDto(pincode);
			operations.put(PINCODE, response.getPincode(), response.getPincode());
			return response;
		}
		throw new BadRequestException("pincode not exist", HttpStatus.BAD_REQUEST);
	}
}

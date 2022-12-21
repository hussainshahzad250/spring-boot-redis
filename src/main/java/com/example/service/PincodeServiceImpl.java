package com.example.service;

import com.example.Constant;
import com.example.assembler.PincodeAssembler;
import com.example.dto.PincodeRequest;
import com.example.dto.PincodeResponse;
import com.example.entity.Pincode;
import com.example.repository.PincodeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
@Service
public class PincodeServiceImpl implements PincodeService, Constant {

    @Autowired
    private PincodeAssembler pincodeAssembler;

    @Autowired
    private PincodeRepository pincodeRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, Long, PincodeResponse> operations;

    @PostConstruct
    private void initializeHashOperation() {
        operations = redisTemplate.opsForHash();
    }

    @Override
    public PincodeResponse addPincode(PincodeRequest pincodeRequest) throws Exception {
        Optional<Pincode> optional = pincodeRepository.findByPincode(pincodeRequest.getPincode());
        if (optional.isPresent())
            throw new Exception("pincode already exist");
        Pincode pincode = pincodeAssembler.dtoToEntity(pincodeRequest);
        pincodeRepository.save(pincode);
        PincodeResponse response = pincodeAssembler.entityToDto(pincode);
        operations.put(PINCODE, pincode.getId(), response);
        return response;
    }

    @Override
    public PincodeResponse getPincode(Long pinCode) throws Exception {
        Optional<Pincode> optional = pincodeRepository.findById(pinCode);
        if (optional.isPresent()) {
            Pincode pincode = optional.get();
            PincodeResponse response = pincodeAssembler.entityToDto(pincode);
            operations.put(PINCODE, response.getId(), response);
            return response;
        }
        throw new Exception("No Data found");
    }
}

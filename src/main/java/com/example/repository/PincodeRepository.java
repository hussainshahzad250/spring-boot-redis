package com.example.repository;

import com.example.entity.Pincode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PincodeRepository extends JpaRepository<Pincode, Long> {

    Optional<Pincode> findByPincode(String pincode);

    List<Pincode> findByPincodeAndCityName(String pincode, String cityName);
}

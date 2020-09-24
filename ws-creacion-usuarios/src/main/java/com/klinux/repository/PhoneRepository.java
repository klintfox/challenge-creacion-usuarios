package com.klinux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klinux.entity.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

	Phone findByPhoneNumber(String phoneNumber);

}

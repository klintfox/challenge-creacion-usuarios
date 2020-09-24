package com.klinux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klinux.entity.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Long>{

    public User findByEmail(String email);

}
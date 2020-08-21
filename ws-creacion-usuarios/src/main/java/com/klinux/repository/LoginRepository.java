package com.klinux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klinux.entity.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Long>{

//	User findByEmail(String email);

	@Query("SELECT u FROM user u WHERE u.email = :email")
    public User findByEmail(@Param("email") String email);

}
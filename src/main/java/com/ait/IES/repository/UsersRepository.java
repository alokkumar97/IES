package com.ait.IES.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.IES.model.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, Serializable> {

	public UsersEntity findByUserEmail(String userEmail);
	public UsersEntity findByUserEmailAndUserPazzwd(String userEmail, String userPazzwd);
	
}

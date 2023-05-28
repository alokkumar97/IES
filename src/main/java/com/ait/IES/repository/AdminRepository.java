package com.ait.IES.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.IES.model.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Serializable> {

	public AdminEntity findByAdminEmailAndPazzwd(String adminEmail, String pazzwd);
	
}

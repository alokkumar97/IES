package com.ait.IES.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.IES.model.UsersRolesEntity;

public interface UserRolesRepository extends JpaRepository<UsersRolesEntity, Serializable> {

	
	
}

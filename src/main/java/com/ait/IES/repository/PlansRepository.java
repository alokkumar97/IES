package com.ait.IES.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.IES.model.PlansEntity;

public interface PlansRepository extends JpaRepository<PlansEntity, Serializable> {

	
	public PlansEntity findByPlansName(String plansName);
}

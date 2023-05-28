package com.ait.IES.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.IES.model.CaseWorkerEntity;

public interface CaseWorkerRepository extends JpaRepository<CaseWorkerEntity, Serializable> {

	public CaseWorkerEntity findByCaseWorkerEmail(String caseWorkerEmail);
	public CaseWorkerEntity findByCaseWorkerEmailAndPazzwd(String caseWorkerEmail, String caseWorkerPazzwd);
	
}

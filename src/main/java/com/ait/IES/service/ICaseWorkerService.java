package com.ait.IES.service;

import java.util.List;

import com.ait.IES.DTO.CaseWorkerRegisterForm;
import com.ait.IES.DTO.LoginForm;
import com.ait.IES.DTO.UnlockForm;
import com.ait.IES.model.CaseWorkerEntity;

public interface ICaseWorkerService {

	public String login(LoginForm login);
	public boolean unlockForm(UnlockForm form);
	
	
	public String registerForm(CaseWorkerRegisterForm form);
	public List<CaseWorkerEntity> getAllCaseWorker();
	public String deleteById(Integer id);
	public String updateForm(CaseWorkerRegisterForm form);
}

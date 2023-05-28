package com.ait.IES.service;

import java.util.List;

import com.ait.IES.DTO.PlansRegisterForm;
import com.ait.IES.model.PlansEntity;

public interface IPlansService {


	public String registerPlan(PlansRegisterForm form);
	public List<PlansEntity> getAllPlans();
	public String deleteById(Integer pId);
	public String updatePlans(PlansRegisterForm form);
	
}

package com.ait.IES.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ait.IES.DTO.PlansRegisterForm;
import com.ait.IES.model.AdminEntity;
import com.ait.IES.model.PlansEntity;
import com.ait.IES.repository.AdminRepository;
import com.ait.IES.repository.PlansRepository;
import com.ait.IES.service.IPlansService;

public class PlansServiceImpl implements IPlansService {
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private PlansRepository plansRepo;
	
	
	@Autowired
	private HttpSession session;

	
	
	@Override
	public String registerPlan(PlansRegisterForm form) {
		
		PlansEntity plan = plansRepo.findByPlansName(form.getPlansName());
		if(plan != null) {
			return "Plan is already exists !!";
		}
		PlansEntity plans = new PlansEntity();
		BeanUtils.copyProperties(form, plans);
		
		Integer admnId = (Integer) session.getAttribute("adminId");
		AdminEntity aid = adminRepo.findById(admnId).get();
		
		plans.setAdmin(aid);		
		plansRepo.save(plans);
		return "success";
	}

	@Override
	public List<PlansEntity> getAllPlans() {		
		return plansRepo.findAll();
	}

	@Override
	public String deleteById(Integer pId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updatePlans(PlansRegisterForm form) {
		PlansEntity plans = plansRepo.findByPlansName(form.getPlansName());
		if(plans == null) {
			return "Plan Name Not found !";
		}
		plans.setCategory(form.getCategory());
		plans.setStartDate(form.getStartDate());
		plans.setEndDate(form.getEndDate());
		plansRepo.save(plans);
		return "success";
	}

}

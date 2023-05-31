package com.ait.IES.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.ait.IES.DTO.PlansRegisterForm;
import com.ait.IES.model.PlansEntity;
import com.ait.IES.model.UsersEntity;
import com.ait.IES.repository.PlansRepository;
import com.ait.IES.repository.UsersRepository;
import com.ait.IES.service.IPlansService;
import com.ait.IES.service.IUserService;

@Service
public class PlansServiceImpl implements IPlansService {
	
	
	@Autowired
	private PlansRepository plansRepo;
	
	@Autowired
	private UsersRepository userRepo;
		
	private UsersEntity getValidUser(Integer userId) {
		Optional<UsersEntity> findById = userRepo.findById(userId);
		if(!findById.isPresent()) {
			throw new RuntimeException("User Not found");			
		}
		return findById.get();
	}
	
	
	@Override
	public String registerPlan(PlansRegisterForm form, Integer userId) {		
		UsersEntity user = getValidUser(userId);
		PlansEntity plan = plansRepo.findByPlansName(form.getPlansName());
		if(plan != null) {
			return "Plan is already exists !!";
		}
		PlansEntity plans = new PlansEntity();
		BeanUtils.copyProperties(form, plans);
		plans.setUser(user);
		LocalDate date = LocalDate.now();
		LocalDateTime dateTime = date.atTime(LocalTime.now());
		plans.setCreatedDate(dateTime);
		plansRepo.save(plans);
		return "success";
	}

	@Override
	public List<PlansEntity> getAllPlans() {		
		return plansRepo.findAll();
	}

	@Override
	public String deleteById(Integer pId) {
		Optional<PlansEntity> findById = plansRepo.findById(pId);
		if(findById != null) {
			 findById.get();
			 return "Successfully deleted !!";
		}
		return "Plan Not Found !";
	}

	@Override
	public String updatePlans(PlansRegisterForm form) {
		PlansEntity plans = plansRepo.findByPlansName(form.getPlansName());
		if(plans == null) {
			return "Plan Name Not found !";
		}
		plans.setCategory(form.getCategory());
		plans.setPlanStartDate(form.getPlanStartDate());
		plans.setPlanEndDate(form.getPlanEndDate());
		LocalDate date = LocalDate.now();
		LocalDateTime dateTime = date.atTime(LocalTime.now());
		plans.setUpdatedDate(dateTime);
		plansRepo.save(plans);
		return "success";
	}

}

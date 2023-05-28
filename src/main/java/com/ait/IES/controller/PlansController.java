package com.ait.IES.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ait.IES.DTO.PlansRegisterForm;
import com.ait.IES.model.PlansEntity;
import com.ait.IES.service.IPlansService;

public class PlansController {

	@Autowired
	private IPlansService planService;
	
	
	@PostMapping("/registerplan")
	public ResponseEntity<String> resisterPlan(@RequestBody PlansRegisterForm form){
		String status = planService.registerPlan(form);
		return new ResponseEntity<String>(status, HttpStatus.CREATED);
	}
	
	@GetMapping("/allplans")
	public ResponseEntity<List<PlansEntity>> getAllPlans(){
		List<PlansEntity> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{pid}")
	public ResponseEntity<String> deleteById(@PathVariable Integer pid){
		String status = planService.deleteById(pid);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateByName(@RequestBody  PlansRegisterForm form){
		String status = planService.updatePlans(form);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}	
	
	
	
}

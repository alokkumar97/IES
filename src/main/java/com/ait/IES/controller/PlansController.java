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
import org.springframework.web.bind.annotation.RestController;

import com.ait.IES.DTO.PlansRegisterForm;
import com.ait.IES.model.PlansEntity;
import com.ait.IES.service.IPlansService;

@RestController("/{userId}")
public class PlansController {

	@Autowired
	private IPlansService planService;
	
	
	@PostMapping("/registerplan/{userId}")
	public ResponseEntity<String> resisterPlan(@RequestBody PlansRegisterForm form, @PathVariable Integer userId){
		String status = planService.registerPlan(form, userId);
		if(status.equals("success")) {
			return new ResponseEntity<String>(status, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>(status, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getallplans")
	public ResponseEntity<List<PlansEntity>> getAllPlans(){
		List<PlansEntity> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{pid}")
	public ResponseEntity<String> deleteById(@PathVariable Integer pid){
		String status = planService.deleteById(pid);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@PutMapping("/updateplan")
	public ResponseEntity<String> updatePlans(@RequestBody  PlansRegisterForm form){
		String status = planService.updatePlans(form);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}	
	
	
	
}

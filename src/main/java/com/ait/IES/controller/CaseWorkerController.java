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

import com.ait.IES.DTO.CaseWorkerRegisterForm;
import com.ait.IES.DTO.LoginForm;
import com.ait.IES.DTO.UnlockForm;
import com.ait.IES.model.CaseWorkerEntity;
import com.ait.IES.service.ICaseWorkerService;

@RestController
public class CaseWorkerController {

	@Autowired
	private ICaseWorkerService cswrkService;
	
	
	@GetMapping("/login")
	public ResponseEntity<String>loginPage(@RequestBody LoginForm form){
		String status = cswrkService.login(form);
		if(status.equals("success")) {
			return new ResponseEntity<String>(status,HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(status,HttpStatus.BAD_REQUEST);
		}		
	}	
	
	@PostMapping("/unlock")
	public ResponseEntity<String>unlockAccount(@RequestBody UnlockForm form){
		boolean status = cswrkService.unlockForm(form);
		if(status) {
			return new ResponseEntity<String>( HttpStatus.OK);
		}else
			return new ResponseEntity<String>( HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCaseWorker(@RequestBody CaseWorkerRegisterForm form){
		String status = cswrkService.registerForm(form);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/allWorkers")
	public ResponseEntity<List<CaseWorkerEntity>> getAllWorkers(){
		List<CaseWorkerEntity> allCaseWorker = cswrkService.getAllCaseWorker();
		return new ResponseEntity<>(allCaseWorker, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id){
		String status = cswrkService.deleteById(id);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateWorker(@RequestBody CaseWorkerRegisterForm form){
		String status = cswrkService.updateForm(form);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	
	
	
	
}

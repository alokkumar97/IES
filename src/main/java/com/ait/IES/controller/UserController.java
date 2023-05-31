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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ait.IES.DTO.LoginForm;
import com.ait.IES.DTO.UnlockForm;
import com.ait.IES.DTO.UserRegisterForm;
import com.ait.IES.model.UsersEntity;
import com.ait.IES.service.IUserService;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;	

	
	@GetMapping("/loginuser")
	public ResponseEntity<String>loginPage(@RequestBody LoginForm form){			
		String status = userService.login(form);
		if(status.equals("success")) {
			return new ResponseEntity<String>(status,HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(status,HttpStatus.BAD_REQUEST);
		}		
	}
	
	@PostMapping("/unlockuser")
	public ResponseEntity<String>unlockAccount(@RequestBody UnlockForm form){
		boolean status = userService.unlockForm(form);
		if(status) {
			return new ResponseEntity<String>( HttpStatus.OK);
		}else
			return new ResponseEntity<String>( HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/forgotpsswd")
	public ResponseEntity<String> forgotpazzwd(@RequestParam String email){
		String forgotPwd = userService.forgotPwd(email);
		if(forgotPwd =="success") {
			return new ResponseEntity<String>(forgotPwd,HttpStatus.OK);
		}
		return new ResponseEntity<String>(forgotPwd,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/registeruser")
	public ResponseEntity<String> registerCaseWorker(@RequestBody UserRegisterForm form){
		String status = userService.registerForm(form);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/allusers")
	public ResponseEntity<List<UsersEntity>> getAllWorkers(){
		List<UsersEntity> allCaseWorker = userService.getAllCaseWorker();
		return new ResponseEntity<>(allCaseWorker, HttpStatus.OK);
	}
	
	@GetMapping("/findById/{uid}")
	public ResponseEntity<UsersEntity> getUsersById(@PathVariable Integer uid){
		UsersEntity status = userService.findById(uid);
		return new ResponseEntity<UsersEntity>(status, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id){
		String status = userService.deleteById(id);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@PutMapping("/updateusers")
	public ResponseEntity<String> updateWorker(@RequestBody UserRegisterForm form){
		String status = userService.updateForm(form);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}	
	
}

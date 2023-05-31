package com.ait.IES.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserRegisterForm {

	private String userFullName;
	private String userEmail;
	private String gender;
	private Long contactNo;
	private LocalDate dob;
	private Long userSSN;
	
}

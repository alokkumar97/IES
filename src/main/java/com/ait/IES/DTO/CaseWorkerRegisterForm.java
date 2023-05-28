package com.ait.IES.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CaseWorkerRegisterForm {

	private String caseWorkerName;
	private String caseWorkerEmail;
	private String gender;
	private Long contactNo;
	private LocalDate dob;
	private Long ssn;
	
}

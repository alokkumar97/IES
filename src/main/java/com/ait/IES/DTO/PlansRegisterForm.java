package com.ait.IES.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PlansRegisterForm {

	
	private String plansName;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private String category;
}

package com.ait.IES.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PlansRegisterForm {

	
	private String plansName;
	private LocalDate startDate;
	private LocalDate endDate;
	private String category;
}

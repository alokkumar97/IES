package com.ait.IES.DTO;

import lombok.Data;

@Data
public class UnlockForm {

	private String userEmail;
	private String tempPwd;
	private String newPazzwd;
	private String confirmPazzwd;
	
}

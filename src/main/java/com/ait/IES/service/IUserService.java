package com.ait.IES.service;

import java.util.List;

import com.ait.IES.DTO.LoginForm;
import com.ait.IES.DTO.UnlockForm;
import com.ait.IES.DTO.UserRegisterForm;
import com.ait.IES.model.UsersEntity;

public interface IUserService {

	public String login(LoginForm login);
	public boolean unlockForm(UnlockForm form);
	public String forgotPwd(String email);
	
	public String registerForm(UserRegisterForm form);
	public List<UsersEntity> getAllCaseWorker();
	public UsersEntity findById(Integer uid);
	public String deleteById(Integer id);
	public String updateForm(UserRegisterForm form);
}

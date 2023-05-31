package com.ait.IES.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.IES.DTO.LoginForm;
import com.ait.IES.DTO.UnlockForm;
import com.ait.IES.DTO.UserRegisterForm;

import com.ait.IES.model.UsersEntity;
import com.ait.IES.repository.UsersRepository;

import com.ait.IES.service.IUserService;
import com.ait.IES.utils.MailsUtil;
import com.ait.IES.utils.PazzwdUtil;

@Service
public class UsersServiceImpl implements IUserService {

	private Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

	@Autowired
	private UsersRepository userRepo;
	

	@Autowired
	private MailsUtil mailUtil;


	@Override
	public String login(LoginForm login) {
		logger.info("Login Functionality Execution started ....");
		UsersEntity user = userRepo.findByUserEmailAndUserPazzwd(login.getUserEmail(), login.getUserPazzwd());
		if (user == null) {
			return "Invalid Credentials";
		}
		if (user.getStatus().equals("LOCKED")) {
			return "Please Unlock your account to access the Application !!";
		}
		logger.info(user.getUserId() + " User login successfully ....");
		return "success";
	}

	@Override
	public boolean unlockForm(UnlockForm form) {
		logger.info("Unlock Functionality Executation started !!");
		UsersEntity user = userRepo.findByUserEmail(form.getUserEmail());
		if (form.getTempPwd().equals(user.getUserPazzwd())) {
			user.setUserPazzwd(form.getNewPazzwd());
			user.setStatus("UNLOCKED");
			userRepo.save(user);
			logger.info("Password Successfully updated !!");
			return true;
		}
		logger.info("Password update Unsuccessfully !!");
		return false;
	}

	@Override
	public String forgotPwd(String email) {
		UsersEntity user = userRepo.findByUserEmail(email);
		if (user == null) {
			return "Invalid Credentials!";
		}
		String subject = "Recover Password";
		String body = "Your Recover password is ::" + user.getUserPazzwd();
		mailUtil.sendMail(subject, body, email);
		return "success";
	}

	/**
	 * +++++++++++++++++++++++++++++++++++++++++++ Case Worker Register Form, List
	 * out all Workers, Delete By Id, Update Case Worker functionality implements
	 * below ++++++++++++++++++++++++++++++++++++++++++++
	 */
	@Override
	public String registerForm(UserRegisterForm form) {
		logger.info("Case worker Registration process started...");
		// If User is already present then return false
		UsersEntity usr = userRepo.findByUserEmail(form.getUserEmail());
		if (usr != null) {
			return "User already exist";
		}
		UsersEntity users = new UsersEntity();
		// Copy properties from SignUp form and Store into Case Worker Object
		BeanUtils.copyProperties(form, users);
		// Generate Temporary Password
		String temPwd = PazzwdUtil.generatePwd();
		logger.info("Tempory password generate successfully...");

		users.setUserPazzwd(temPwd);
		LocalDate date = LocalDate.now();
		LocalDateTime dateTime = date.atTime(LocalTime.now());
		users.setCreatedDate(dateTime);
		// save Case Worker Object into Case Worker DB
		userRepo.save(users);
		logger.info("record saved into users DB successfully with temporary password ...");
		logger.info("Sending password to the mail process started...");
		String subject = "Unlock your account !";
		String to = form.getUserEmail();
		StringBuffer body = new StringBuffer();
		body.append("<h2>Use below temporary password to unlock account</h2>");
		body.append("Temporary password : " + temPwd);
		body.append("<br/>");
		body.append("<a href=\"http://localhost:8080/unlock?userEmail=" + to + "\">Click here to Unlock Account.</a>");
		mailUtil.sendMail(subject, body.toString(), to);
		logger.info(" Password sent successfully to the user email...");
		logger.info("Case worker Registration process successfully completed...");
		return "success";
	}

	@Override
	public List<UsersEntity> getAllCaseWorker() {
		logger.info("Case worker findAll process started...");
		List<UsersEntity> findAllUsers = userRepo.findAll();
		logger.info("Case worker findAll process successfully completed...");
		return findAllUsers;
	}
	
	@Override
	public UsersEntity findById(Integer uid) {
		Optional<UsersEntity> findById = userRepo.findById(uid);
		if(findById != null) {			
			return findById.get();
		}
		return null;
	}

	@Override
	public String deleteById(Integer id) {
		logger.info("Case worker deleteById process started...");
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
			logger.info("Case worker deleteById successfully completed...");
			return "Successfully Deleted";
		} else
			logger.info("Case worker Not Found on that Id..." + id);
		return id + " Not found !";
	}

	@Override
	public String updateForm(UserRegisterForm form) {
		logger.info("User update process started...");
		UsersEntity user = userRepo.findByUserEmail(form.getUserEmail());
		if (user != null) {
			user.setUserFullName(form.getUserFullName());
			user.setContactNo(form.getContactNo());
			user.setDob(form.getDob());
			LocalDate date = LocalDate.now();
			LocalDateTime dateTime = date.atTime(LocalTime.now());
			user.setUpdatedDate(dateTime);
			userRepo.save(user);
			logger.info("User update process successfully completed...");
			return "Success";
		}
		logger.info("User update process successfully completed...");
		return "User Not exist";
	}
}

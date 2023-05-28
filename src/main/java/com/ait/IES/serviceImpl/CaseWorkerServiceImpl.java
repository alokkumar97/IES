package com.ait.IES.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.IES.DTO.CaseWorkerRegisterForm;
import com.ait.IES.DTO.LoginForm;
import com.ait.IES.DTO.UnlockForm;
import com.ait.IES.model.AdminEntity;
import com.ait.IES.model.CaseWorkerEntity;
import com.ait.IES.repository.AdminRepository;
import com.ait.IES.repository.CaseWorkerRepository;
import com.ait.IES.service.ICaseWorkerService;
import com.ait.IES.utils.PazzwdUtil;

@Service
public class CaseWorkerServiceImpl implements ICaseWorkerService {
	
	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private CaseWorkerRepository caseWrkRepo;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public String login(LoginForm login) {
		
		if(login.getUserEmail() != null) {
			Optional<AdminEntity> admin = Optional.ofNullable(adminRepo.findByAdminEmailAndPazzwd(login.getUserEmail(), login.getPazzwd()));
			if(login.getUserEmail().equals(admin.get().getAdminEmail())) {
				session.setAttribute("adminId", admin);
				System.out.println("Admin Logged in");
				return "success";
			}else {
				CaseWorkerEntity caseWrk = caseWrkRepo.findByCaseWorkerEmailAndPazzwd(login.getUserEmail(), login.getPazzwd());
				if(login.getUserEmail().equals(caseWrk.getCaseWorkerEmail()) && caseWrk.getStatus() == "LOCKED") {
					return "Your account is LOCKED, please UNLOCK your account to access the application ! ";
				}else {
					session.setAttribute("cwId", caseWrk);
					System.out.println("Caseworker Logged in");
					return "success";
				}			
			}			
		}
		return "Invalid Credentials !!";
	}

	@Override
	public boolean unlockForm(UnlockForm form) {
		CaseWorkerEntity caseWrk = caseWrkRepo.findByCaseWorkerEmail(form.getUserEmail());
		if(caseWrk.getPazzwd().equals(form.getTempPwd())) {
			caseWrk.setPazzwd(form.getNewPazzwd());
			caseWrk.setStatus("UNLOCKED");
			caseWrkRepo.save(caseWrk);
			return true;
		}else
		return false;
	}
/**+++++++++++++++++++++++++++++++++++++++++++
 * Case Worker Register Form,
 * List out all Workers,
 * Delete By Id,
 * Update Case Worker 
 * functionality implements below
 * ++++++++++++++++++++++++++++++++++++++++++++
 * */
	@Override
	public String registerForm(CaseWorkerRegisterForm form) {
		// If User is already present then return false 
		CaseWorkerEntity user = caseWrkRepo.findByCaseWorkerEmail(form.getCaseWorkerEmail());
		if(user != null) {
			return "User already exist";
		}		
		CaseWorkerEntity workerRegister = new CaseWorkerEntity();
		//Copy properties from SignUp form and Store into Case Worker Object
		BeanUtils.copyProperties(form, workerRegister);
		//Generate Temporary Password
		String temPwd = PazzwdUtil.generatePwd();		
		
		//Get Admin Id from session
//		Integer admnId = (Integer) session.getAttribute("adminId");
//		AdminEntity aid = adminRepo.findById(admnId).get();
		//Store Admin id in Case Worker DB
//		workerRegister.setAdmin(aid);		
		
		workerRegister.setPazzwd(temPwd);
		workerRegister.setStatus("LOCKED");
		//save Case Worker Object into Case Worker DB
		caseWrkRepo.save(workerRegister);
		//TODO : Email Implementation
		return "success";
	}

	@Override
	public List<CaseWorkerEntity> getAllCaseWorker() {
		return caseWrkRepo.findAll();
	}

	@Override
	public String deleteById(Integer id) {
		if(caseWrkRepo.existsById(id)) {
			caseWrkRepo.deleteById(id);
			return "Successfully Deleted";
		}else
		return id+" Not found !";
	}
	
	@Override
	public String updateForm(CaseWorkerRegisterForm form) {
		CaseWorkerEntity user = caseWrkRepo.findByCaseWorkerEmail(form.getCaseWorkerEmail());
		if(user != null) {
			user.setCaseWorkerName(form.getCaseWorkerName());
			user.setContactNo(form.getContactNo());
			user.setDob(form.getDob());
			caseWrkRepo.save(user);
			return "Success";			
		}
		return "User Not exist";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

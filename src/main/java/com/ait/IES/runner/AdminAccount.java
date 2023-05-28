package com.ait.IES.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ait.IES.model.AdminEntity;
import com.ait.IES.repository.AdminRepository;

@Component
public class AdminAccount implements CommandLineRunner{

	@Autowired
	private AdminRepository repo;
	
	@Override
	public void run(String... args) throws Exception {
		repo.deleteAll();
		AdminEntity admin = new AdminEntity();
		admin.setAdminId(1);
		admin.setAdminName("Admin");
		admin.setPazzwd("Admin123");
		admin.setAdminEmail("alokkumarmallick551@gmail.com");
		admin.setContactNo(9090177321l);
		admin.setIsAdmin(true);
		admin.setPazzwd("Admin123");
		
		List<AdminEntity> list = Arrays.asList(admin);
		repo.saveAll(list);
		System.out.println("Admin saved!!!");
		
	}

}

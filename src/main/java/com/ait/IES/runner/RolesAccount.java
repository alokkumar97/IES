package com.ait.IES.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ait.IES.model.UsersRolesEntity;
import com.ait.IES.repository.UserRolesRepository;

@Component
public class RolesAccount implements CommandLineRunner{

	@Autowired
	private UserRolesRepository repo;
	
	@Override
	public void run(String... args) throws Exception {
		repo.deleteAll();
		UsersRolesEntity role1 = new UsersRolesEntity();
		 role1.setRoleId(1);
		 role1.setRoleName("ADMIN");
		UsersRolesEntity role2 = new UsersRolesEntity();
		 role2.setRoleId(2);
		 role2.setRoleName("EMPLOYEE");
		 
		 List<UsersRolesEntity> list = Arrays.asList(role1, role2);
		repo.saveAll(list);
		System.out.println("Roles saved!!!");
		
	}

}

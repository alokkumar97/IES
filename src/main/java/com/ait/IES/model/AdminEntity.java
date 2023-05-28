package com.ait.IES.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="admin_tab")
@Data
public class AdminEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="admin_id_col")
	private Integer adminId;
	@Column(name="admin_name_col")
	private String adminName;
	@Column(name="admin_email_col")
	private String adminEmail;
	@Column(name="admin_password_col")
	private String pazzwd;
	@Column(name="admin_contact_no_col")
	private Long contactNo;
	@Column(name="isAdmin")
	private Boolean isAdmin = true;
	
	@OneToMany(mappedBy = "admin", 
							cascade = CascadeType.ALL, 
							fetch = FetchType.EAGER)
	private List<CaseWorkerEntity> entities;
	
	@OneToMany(mappedBy = "admin", 
							cascade = CascadeType.ALL, 
							fetch = FetchType.LAZY)
	private List<PlansEntity> plans;
	
}

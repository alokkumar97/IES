package com.ait.IES.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="users_tab")
@Data
public class UsersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id_col")
	private Integer userId;
	
	@Column(name="user_name_col")
	private String userFullName;
	
	@Column(name="user_email_col")
	private String userEmail;
	
	@Column(name="user_gender_col")
	private String gender;
	
	@Column(name="user_password_col")
	private String userPazzwd;
	
	@Column(name="user_dob_col")
	private LocalDate dob;
	
	@Column(name="user_contact_no_col")
	private Long contactNo;
	
	@Column(name="user_ssn_no_col")
	private Long userSSN;
	
	@Column(name="isActive")
	private Boolean isActive = true;
	
	@Column(name="user_status_col")
	private String status ="LOCKED";
	
	@Column(name="create_date_col")
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Column(name="update_date_col")
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	

	@JoinColumn(name="created_By_col")
	private String createdBy;

	@JoinColumn(name="updated_By_col")
	private String updatedBy;
	
	@ManyToOne
	@JoinColumn(name="role_fk_id")
	private UsersRolesEntity role;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<PlansEntity> plans;
	
	@OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PlansEntity> createPlansBy;
	
	@OneToMany(mappedBy = "updatedBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PlansEntity> updatePlans;
	
	
	
}

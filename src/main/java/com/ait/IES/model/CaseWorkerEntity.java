package com.ait.IES.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="case_worker_tab")
@Data
public class CaseWorkerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cw_id_col")
	private Integer caseWorkerId;
	@Column(name="cw_name_col")
	private String caseWorkerName;
	@Column(name="cw_email_col")
	private String caseWorkerEmail;
	@Column(name="cw_gender_col")
	private String gender;
	@Column(name="cw_password_col")
	private String pazzwd;
	@Column(name="cw_dob_col")
	private LocalDate dob;
	@Column(name="cw_contact_no_col")
	private Long contactNo;
	@Column(name="cw_ssn_no_col")
	private Long ssn;
	@Column(name="isActive")
	private Boolean isActive = true;
	@Column(name="cw_status_col")
	private String status;
	
	@ManyToOne
	@JoinColumn(name="admin_fk_id")
	private AdminEntity admin;
	
	
	
}

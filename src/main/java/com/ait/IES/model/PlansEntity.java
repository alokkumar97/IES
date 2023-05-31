package com.ait.IES.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="plans_tab")
@Data
public class PlansEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="plan_id_col")
	private Integer plansId;
	@Column(name="plan_name_col")
	private String plansName;	
	@Column(name="plan_category_col")
	private String category;
	
	@CreationTimestamp
	@Column(name="plan_start_date_col")
	private LocalDate planStartDate;	
	@UpdateTimestamp
	@Column(name="plan_end_date_col")
	private LocalDate planEndDate;
	
	@Column(name="isActive")
	private Boolean isActive = true;
	
	@Column(name="created_date_col")
	private LocalDateTime createdDate;
	@Column(name="updated_date_col")
	private LocalDateTime updatedDate;
	
	@ManyToOne
	@JoinColumn(name="plan_created_by_col")
	private UsersEntity createdBy;	
	
	@ManyToOne
	@JoinColumn(name="plan_updated_by_col")
	private UsersEntity updatedBy;	
	
	@ManyToOne
	@JoinColumn(name="user_id_fk")
	private UsersEntity user;
	
	
	
}

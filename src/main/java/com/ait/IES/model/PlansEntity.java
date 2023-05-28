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
	private LocalDate startDate;
	
	@UpdateTimestamp
	@Column(name="plan_end_date_col")
	private LocalDate endDate;
	
	@Column(name="isActive")
	private Boolean isActive = true;
	
	@ManyToOne
	@JoinColumn(name="admin_id_fk")
	private AdminEntity admin;
	
	
	
}

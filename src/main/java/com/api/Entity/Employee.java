package com.api.Entity;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Employee {
	
	@Id
	private String id;
	
	@Column(unique = true)
	private String empid;

	private String fname;

	private String dob;

	private String doj;

	private Integer salary;

	private String reportsto;
	
	@CreationTimestamp
	private String createdat;

	@UuidGenerator
	private String client_reqid;
	
	@ManyToOne()
	@JsonManagedReference
	private Department deptid; // Department

	@ManyToOne()
	@JsonManagedReference
	private Ranks rankid; // Ranks

}
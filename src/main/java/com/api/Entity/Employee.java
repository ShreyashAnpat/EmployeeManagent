package com.api.Entity;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
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
	
	@UpdateTimestamp
	private String updatedat;

}

package com.api.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Ranks {
	
	@Id
	private String id ;
	
	private String rankdesc ;
	
	private String parentrankid ;
	
	@OneToMany( mappedBy = "rankid" , cascade = CascadeType.ALL)
	@JsonBackReference
	@ToString.Exclude
	private List<Employee> emp ;
	
	
}

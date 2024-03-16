package com.api.DAO;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class RankDao {
	
	private String id ;
	
	private String rankdesc ;
	
	private String parentrankid ;
}

package com.api.DAO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RankDao {
	
	@NotNull @NotBlank(message = "id is mandatory")
	private String id ;
	
	@NotNull @NotBlank(message = "rankdesc is mandatory")
	private String rankdesc ;
	
	@NotNull @NotBlank(message = "parentrankid is mandatory")
	private String parentrankid ;
}

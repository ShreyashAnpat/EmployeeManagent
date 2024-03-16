package com.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.Entity.Ranks;

@Repository
public interface RankRepo extends JpaRepository<Ranks, String>{
	
	
	
}

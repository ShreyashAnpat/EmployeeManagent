package com.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.DAO.RankDao;
import com.api.Entity.Ranks;
import com.api.repo.RankRepo;

@Service
public class RankService {

	@Autowired
	private RankRepo repo;

	
	public ResponseEntity<?> getAllRank() {
		List<Ranks> rankes = repo.findAll() ;
		return	new ResponseEntity<>(rankes , HttpStatus.OK) ;
		
	}
	
	
	public ResponseEntity<?> addRank(RankDao rankDao) {

		if (rankDao.getId().isEmpty() || rankDao.getRankdesc().isEmpty()) {
			return new ResponseEntity<>("Rank ID or RankDesc is black", HttpStatus.BAD_REQUEST);
		} else {
			Ranks rank = new Ranks();

			rank.setId(rankDao.getId());
			rank.setParentrankid(rankDao.getParentrankid());
			rank.setRankdesc(rankDao.getRankdesc());
			repo.save(rank);
			return new ResponseEntity<>(rank, HttpStatus.OK);

		}
	}

	public ResponseEntity<?> getRankById(String rankId) {

		
		if (rankId.isEmpty() ) {
			
			return new ResponseEntity<>("Rank ID is black", HttpStatus.BAD_REQUEST);
		} else {
			
			Optional<Ranks> rank = repo.findById(rankId);
			
			if(rank.isPresent()) {
			
				return new ResponseEntity<>(rank.get() , HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Rank Not Found For id = {"+rankId+"}" , HttpStatus.BAD_REQUEST);
			}
					
		}
	}
}

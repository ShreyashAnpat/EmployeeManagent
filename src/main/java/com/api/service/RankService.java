package com.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.DAO.RankDao;
import com.api.DAO.ResponceHandler;
import com.api.Entity.Ranks;
import com.api.Exception.RunTimeException;
import com.api.repo.RankRepo;

@Service
public class RankService {

	@Autowired
	private RankRepo repo;

	public ResponseEntity<?> getAllRank() {
		List<Ranks> rankes = repo.findAll();
		if (rankes.size() > 0) {
			return ResponceHandler.responceHandler(rankes, HttpStatus.OK, "");
		} else
			throw new RunTimeException("Rank Not Found", HttpStatus.NOT_FOUND, "");

	}

	public ResponseEntity<?> addRank(RankDao rankDao) {
		
		Boolean  isRankPresent = repo.findById(rankDao.getId()).isPresent();
		
		if(isRankPresent) throw new RunTimeException("Rank Already Exist.", HttpStatus.NOT_FOUND, "");

		Ranks rank = new Ranks();
		rank.setId(rankDao.getId());
		rank.setParentrankid(rankDao.getParentrankid());
		rank.setRankdesc(rankDao.getRankdesc());
		repo.save(rank);
		return ResponceHandler.responceHandler("Rank Added Successfuly.", HttpStatus.OK, "");

	}

	public ResponseEntity<?> getRankById(String rankId) {
		
		if(rankId.isBlank())  new RunTimeException("RankId is Empty", HttpStatus.BAD_REQUEST, "");
		
		Optional<Ranks> rank = repo.findById(rankId);

		if (rank.isPresent()) {
			return ResponceHandler.responceHandler(rank.get(), HttpStatus.OK, "");
		} else {
			throw new RunTimeException("Rank Not Found For id = " + rankId, HttpStatus.BAD_REQUEST, "");
		}

	}

}

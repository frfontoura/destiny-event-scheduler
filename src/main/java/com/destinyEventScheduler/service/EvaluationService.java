package com.destinyEventScheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.model.Evaluation;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.repository.EvaluationRepository;

@Service
public class EvaluationService {

	@Autowired
	private EvaluationRepository evaluationRepository;
	
	@Transactional
	public Evaluation addEvaluation(Long membership, Evaluation evaluation) {
		evaluation.setMemberA(new Member(membership));
		return evaluationRepository.save(evaluation);
	}

	public List<Evaluation> getByGameId(Long gameId) {
		return evaluationRepository.findByGameId(gameId);
	}

}

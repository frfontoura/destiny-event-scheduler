package com.destinyEventScheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.model.Evaluation;
import com.destinyEventScheduler.model.Game;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.repository.EvaluationRepository;

@Service
public class EvaluationService {

	@Autowired
	private EvaluationRepository evaluationRepository;
	
	@Autowired
	private MemberService memberService;
	
	@Transactional
	public void addEvaluations(Long gameId, Long membership, List<Evaluation> evaluations) {
		evaluations.stream().forEach(e -> {
			setMemberLikeDislike(e);
			e.setMemberA(new Member(membership));
			e.setGame(new Game(gameId));
			evaluationRepository.save(e);
		});
	}

	public List<Evaluation> getByGameId(Long gameId) {
		return evaluationRepository.findByGameId(gameId);
	}

	private void setMemberLikeDislike(Evaluation e) {
		Member memberB = memberService.getByMembership(e.getMemberB().getMembership());
		switch (e.getRate()) {
		case LIKE:
			memberB.setLikes(memberB.getLikes() +1);
			break;
		case DISLIKE:
			memberB.setDislikes(memberB.getDislikes() +1);
			break;
		default:
			break;
		}
	}
}

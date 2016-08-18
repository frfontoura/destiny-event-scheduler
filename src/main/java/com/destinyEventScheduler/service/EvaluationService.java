package com.destinyEventScheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.dto.MemberHistoryDTO;
import com.destinyEventScheduler.model.Evaluation;
import com.destinyEventScheduler.model.Game;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.repository.EvaluationRepository;
import com.google.common.collect.Ordering;

@Service
public class EvaluationService {

	@Autowired
	private EvaluationRepository evaluationRepository;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private GameService gameService;
	
	@Transactional
	public void addEvaluations(Long gameId, Long membership, List<Evaluation> evaluations) {
		evaluations.stream().forEach(e -> {
			setMemberLikeDislike(e);
			e.setMemberA(new Member(membership));
			e.setGame(new Game(gameId));
			evaluationRepository.save(e);
		});
	}

	public List<Evaluation> getMemberGameEvaluation(Long membership, Long gameId) {
		Game game = gameService.getGameById(gameId);
		return game.getEvaluationsByMember(new Member(membership));
	}

	public List<MemberHistoryDTO> getGameHistory(Long gameId) {
		Game game = gameService.getGameById(gameId);
		Ordering<MemberHistoryDTO> orderByEntries = Ordering.explicit(game.getMembershipOrderByEntry()).onResultOf(m -> m.getMembership());
		List<MemberHistoryDTO> gameHistory = evaluationRepository.getGameHistory(gameId);
		return orderByEntries.immutableSortedCopy(gameHistory);
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

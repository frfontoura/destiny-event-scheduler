package com.destinyEventScheduler.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.destinyEventScheduler.dto.MemberHistoryDTO;
import com.destinyEventScheduler.enums.Rate;
import com.destinyEventScheduler.model.Evaluation;
import com.destinyEventScheduler.model.QEvaluation;
import com.destinyEventScheduler.repository.EvaluationCustomRepository;
import com.destinyEventScheduler.repository.expressions.EvaluationExpressions;
import com.querydsl.core.types.Projections;

@Repository
public class EvaluationRepositoryImpl extends QueryDslRepositorySupport implements EvaluationCustomRepository {

	private QEvaluation qEvaluation = QEvaluation.evaluation;
	
	public EvaluationRepositoryImpl() {
		super(Evaluation.class);
	}
	
	@Override
	public List<MemberHistoryDTO> getGameHistory(Long gameId) {
		return getQuerydsl().createQuery()
				.select(Projections.bean(MemberHistoryDTO.class, 
						qEvaluation.memberB.membership, 
						qEvaluation.memberB.favoriteEvent.id,
						qEvaluation.memberB.name, 
						qEvaluation.memberB.icon, 
						EvaluationExpressions.rate(Rate.LIKE).sum().as("totalLikes"), 
						EvaluationExpressions.rate(Rate.DISLIKE).sum().as("totalDislikes")))
				.from(qEvaluation)
				.where(qEvaluation.game.id.eq(gameId))
				.groupBy(qEvaluation.memberB.membership, qEvaluation.memberB.favoriteEvent.id, qEvaluation.memberB.name, qEvaluation.memberB.icon)
				.fetch();
	}
}

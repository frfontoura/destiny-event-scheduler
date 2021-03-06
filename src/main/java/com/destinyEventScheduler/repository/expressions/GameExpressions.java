package com.destinyEventScheduler.repository.expressions;

import com.destinyEventScheduler.enums.Status;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.model.QGame;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public abstract class GameExpressions {

	public static BooleanBuilder status(Member member, Status status){
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QGame qGame = QGame.game;
		
		BooleanExpression statusNew = qGame.status.eq(Status.NEW);
		BooleanExpression statusWaintg = qGame.status.eq(Status.WAITING).and(joined(member, Boolean.TRUE));
		BooleanExpression statusValidated = qGame.status.eq(Status.VALIDATED).and(joined(member, Boolean.TRUE).and(qGame.evaluations.any().memberA.eq(member).not()));
		
		if (Status.NEW.equals(status)) {
			booleanBuilder.and(statusNew);
		} else if (Status.WAITING.equals(status)) {
			booleanBuilder.and(statusWaintg);
			booleanBuilder.or(statusValidated);
		} else if (Status.VALIDATED.equals(status)) {
			booleanBuilder.and(statusValidated);
		} else {
			booleanBuilder.and(statusNew);
			booleanBuilder.or(statusWaintg);
			booleanBuilder.or(statusValidated);
		}

		return booleanBuilder;
	}
	
	public static BooleanBuilder joined(Member member, Boolean joined){
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QGame qGame = QGame.game;
		BooleanExpression hasMember = qGame.entries.any().member.eq(member);
		
		if(Boolean.TRUE.equals(joined)){
			booleanBuilder.and(hasMember);
		} else if(Boolean.FALSE.equals(joined)){
			booleanBuilder.and(hasMember.not());
		}

		return booleanBuilder;
	}
	
	public static BooleanBuilder evaluated(Member member, Boolean joined){
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QGame qGame = QGame.game;
		BooleanExpression hasMember = qGame.evaluations.any().memberA.eq(member);
		
		if(Boolean.TRUE.equals(joined)){
			booleanBuilder.and(hasMember);
		} else if(Boolean.FALSE.equals(joined)){
			booleanBuilder.and(hasMember.not());
		}

		return booleanBuilder;
	}
	
}
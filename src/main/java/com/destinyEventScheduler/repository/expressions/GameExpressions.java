package com.destinyEventScheduler.repository.expressions;

import com.destinyEventScheduler.enums.Status;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.model.QGame;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public abstract class GameExpressions {

	public static BooleanBuilder status(Status status){
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QGame qGame = QGame.game;
		
		BooleanExpression statusNew = qGame.status.eq(Status.NEW);
		BooleanExpression statusWaintg = qGame.status.eq(Status.WAITING);
		BooleanExpression statusValidated = qGame.status.eq(Status.VALIDATED);
		
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
	
	public static BooleanBuilder joined(Member member, Status status, Boolean joined){
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QGame qGame = QGame.game;
		BooleanExpression hasMember = qGame.entries.any().member.eq(member);
		
		if(Boolean.TRUE.equals(joined) || Status.WAITING.equals(status) || Status.VALIDATED.equals(status)){
			booleanBuilder.and(hasMember);
		} else if(Boolean.FALSE.equals(joined)){
			booleanBuilder.and(hasMember.not());
		}

		return booleanBuilder;
	}
	
}
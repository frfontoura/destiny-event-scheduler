package com.destinyEventScheduler.repository.expressions;

import com.destinyEventScheduler.enums.Rate;
import com.destinyEventScheduler.model.QEvaluation;
import com.querydsl.core.types.dsl.NumberExpression;

public abstract class EvaluationExpressions {

	public static NumberExpression<Integer> rate(Rate rate){
		QEvaluation qEvaluation = QEvaluation.evaluation;
		return qEvaluation.rate
				.when(rate)
				.then(new Integer(1))
				.otherwise(new Integer(0));
	}

}

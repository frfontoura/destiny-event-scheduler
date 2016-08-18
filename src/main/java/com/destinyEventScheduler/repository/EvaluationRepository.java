package com.destinyEventScheduler.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.destinyEventScheduler.model.Evaluation;

public interface EvaluationRepository extends CrudRepository<Evaluation, Long>, QueryDslPredicateExecutor<Evaluation>, EvaluationCustomRepository {

}

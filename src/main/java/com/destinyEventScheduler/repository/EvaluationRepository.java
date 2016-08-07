package com.destinyEventScheduler.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.destinyEventScheduler.model.Evaluation;

public interface EvaluationRepository extends CrudRepository<Evaluation, Long> {

	public List<Evaluation> findByGameId(Long gameId);

}

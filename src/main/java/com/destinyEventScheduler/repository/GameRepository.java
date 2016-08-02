package com.destinyEventScheduler.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.destinyEventScheduler.model.Game;

public interface GameRepository extends CrudRepository<Game, Long>, QueryDslPredicateExecutor<Game>, GameCustomRepository {

}

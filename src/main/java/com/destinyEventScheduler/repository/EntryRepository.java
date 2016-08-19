package com.destinyEventScheduler.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.destinyEventScheduler.model.Entry;

public interface EntryRepository extends CrudRepository<Entry, Long>, QueryDslPredicateExecutor<Entry>, EntryCustomRepository{

}

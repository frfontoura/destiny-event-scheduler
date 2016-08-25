package com.destinyEventScheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.destinyEventScheduler.model.LogApp;

@Repository
public interface LogAppRepository extends CrudRepository<LogApp, Long> {

}

package com.destinyEventScheduler.repository;

import org.springframework.data.repository.CrudRepository;

import com.destinyEventScheduler.model.EventType;

public interface EventTypeRepository extends CrudRepository<EventType, Long> {

}

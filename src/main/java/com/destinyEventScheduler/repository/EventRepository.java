package com.destinyEventScheduler.repository;

import org.springframework.data.repository.CrudRepository;

import com.destinyEventScheduler.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}

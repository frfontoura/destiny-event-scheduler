package com.destinyEventScheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.model.Event;
import com.destinyEventScheduler.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	public Iterable<Event> getEventList(){
		return eventRepository.findAll();
	}

	public Event getById(long id) {
		return eventRepository.findOne(id);
	}
	
}

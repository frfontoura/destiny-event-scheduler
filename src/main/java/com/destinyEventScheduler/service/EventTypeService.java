package com.destinyEventScheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.model.EventType;
import com.destinyEventScheduler.repository.EventTypeRepository;

@Service
public class EventTypeService {

	@Autowired
	private EventTypeRepository eventTypeRepository;
	
	public Iterable<EventType> getEventTypeList(){
		return eventTypeRepository.findAll();
	}
	
}

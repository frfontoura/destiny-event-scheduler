package com.destinyEventScheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.model.EventType;
import com.destinyEventScheduler.service.EventTypeService;

@RestController
@RequestMapping(value = "/event-types")
public class EventTypeController {

	@Autowired
	private EventTypeService eventTypeService;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<EventType> getEventTypeList(){
		return eventTypeService.getEventTypeList();
	}
	
}

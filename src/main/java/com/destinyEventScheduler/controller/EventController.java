package com.destinyEventScheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.model.Event;
import com.destinyEventScheduler.service.EventService;

@RestController
@RequestMapping(value = "/events")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Event> getEventTypeList(@RequestParam(value = "initialId", defaultValue = "0") Long initialId){
		return eventService.getEventGreaterThanList(initialId);
	}
	
}

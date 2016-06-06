package com.destinyEventScheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.adpter.EventAdapter;
import com.destinyEventScheduler.dto.EventDTO;
import com.destinyEventScheduler.service.EventService;

@RestController
@RequestMapping(value = "/events")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private EventAdapter eventAdapter;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<EventDTO> getEventTypeList(){
		return eventAdapter.convertToDTO(eventService.getEventList());
	}
	
}

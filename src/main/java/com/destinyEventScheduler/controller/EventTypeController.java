package com.destinyEventScheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.adpter.EventTypeAdapter;
import com.destinyEventScheduler.dto.EventTypeDTO;
import com.destinyEventScheduler.service.EventTypeService;

@RestController
@RequestMapping(value = "/event-types")
public class EventTypeController {

	@Autowired
	private EventTypeService eventTypeService;
	
	@Autowired
	private EventTypeAdapter eventTypeAdapter;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<EventTypeDTO> getEventTypeList(){
		return eventTypeAdapter.convertToDTO(eventTypeService.getEventTypeList());
	}
	
}

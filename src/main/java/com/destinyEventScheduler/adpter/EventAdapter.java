package com.destinyEventScheduler.adpter;

import org.springframework.stereotype.Component;

import com.destinyEventScheduler.dto.EventDTO;
import com.destinyEventScheduler.model.Event;

@Component
public class EventAdapter extends DefaultAdapter<Event, EventDTO> {

	@Override
	public EventDTO convertToDTO(Event event) {
		if(event == null){
			return null;
		}
		EventDTO eventDTO = new EventDTO();
		eventDTO.setId(event.getId());
		eventDTO.setName(event.getName());
		eventDTO.setIcon(event.getIcon());
		eventDTO.setMinLight(event.getMinLight());
		eventDTO.setMaxGuardians(event.getMaxGuardians());
		return eventDTO;
	}

}
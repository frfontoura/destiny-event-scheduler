package com.destinyEventScheduler.adpter;

import org.springframework.stereotype.Component;

import com.destinyEventScheduler.dto.EventTypeDTO;
import com.destinyEventScheduler.model.EventType;

@Component
public class EventTypeAdapter extends DefaultAdapter<EventType, EventTypeDTO>{

	@Override
	public EventTypeDTO convertToDTO(EventType eventType) {
		if(eventType == null){
			return null;
		}
		EventTypeDTO eventTypeDTO = new EventTypeDTO();
		eventTypeDTO.setId(eventType.getId());
		eventTypeDTO.setName(eventType.getName());
		eventTypeDTO.setIcon(eventType.getIcon());
		return eventTypeDTO;
	}

}

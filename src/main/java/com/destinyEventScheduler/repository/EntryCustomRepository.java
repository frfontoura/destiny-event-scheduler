package com.destinyEventScheduler.repository;

import java.util.List;

import com.destinyEventScheduler.dto.FavoriteEventDTO;
import com.destinyEventScheduler.dto.PlayedTypeDTO;

public interface EntryCustomRepository {

	public List<PlayedTypeDTO> getEventTypeCountByMember(Long membership);
	public FavoriteEventDTO getFavoriteEventId(Long membership);
	
}

package com.destinyEventScheduler.repository;

import java.util.List;

import com.destinyEventScheduler.dto.PlayedTypeDTO;

public interface EntryCustomRepository {

	public List<PlayedTypeDTO> getEventCountByMember(Long membership);
	
}

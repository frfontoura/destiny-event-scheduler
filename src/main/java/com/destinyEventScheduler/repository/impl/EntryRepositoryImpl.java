package com.destinyEventScheduler.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.destinyEventScheduler.dto.PlayedTypeDTO;
import com.destinyEventScheduler.model.Event;
import com.destinyEventScheduler.model.QEntry;
import com.destinyEventScheduler.repository.EntryCustomRepository;
import com.querydsl.core.types.Projections;

@Repository
public class EntryRepositoryImpl extends QueryDslRepositorySupport implements EntryCustomRepository{

	private QEntry qEntry = QEntry.entry;
	
	public EntryRepositoryImpl(){
		super(Event.class);
	}

	@Override
	public List<PlayedTypeDTO> getEventCountByMember(Long membership) {
		return getQuerydsl().createQuery()
				.select(Projections.bean(PlayedTypeDTO.class, 
						qEntry.game.event.id.as("eventId"),
						qEntry.game.event.name.as("eventName"), 
						qEntry.game.event.icon.as("eventIcon")
						//qEntry.game.event.eventType.id.as("eventTypeId")
						//qEntry.game.event.eventType.name.as("eventTypeName")
						)
					)
				.from(qEntry)
				.where(qEntry.member.membership.eq(membership))
				.fetch();
	}

}

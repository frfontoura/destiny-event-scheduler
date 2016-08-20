package com.destinyEventScheduler.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.destinyEventScheduler.dto.PlayedTypeDTO;
import com.destinyEventScheduler.model.Event;
import com.destinyEventScheduler.model.QEntry;
import com.destinyEventScheduler.repository.EntryCustomRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;

@Repository
public class EntryRepositoryImpl extends QueryDslRepositorySupport implements EntryCustomRepository{

	private QEntry qEntry = QEntry.entry;
	
	public EntryRepositoryImpl(){
		super(Event.class);
	}

	@Override
	public List<PlayedTypeDTO> getEventTypeCountByMember(Long membership) {
		return getQuerydsl().createQuery()
				.select(Projections.bean(PlayedTypeDTO.class, 
						qEntry.game.event.eventType.id.as("eventTypeId"),
						qEntry.game.event.eventType.name.as("eventTypeName"),
						Wildcard.count.as("timesPlayed")
						)
					)
				.from(qEntry)
				.where(qEntry.member.membership.eq(membership))
				.groupBy(qEntry.game.event.eventType.id, qEntry.game.event.eventType.name)
				.fetch();
	}
	
	@Override
	public Long getFavoriteEventId(Long membership){
		 return getQuerydsl().createQuery()
				 .select(qEntry.game.event.id)
				 .from(qEntry)
				 .where(qEntry.member.membership.eq(membership))
				 .groupBy(qEntry.game.event.id)
				 .orderBy(Wildcard.count.desc())
				 .fetchFirst();
	}

}

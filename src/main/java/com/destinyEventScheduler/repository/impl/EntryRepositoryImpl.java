package com.destinyEventScheduler.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.destinyEventScheduler.dto.FavoriteEventDTO;
import com.destinyEventScheduler.dto.PlayedTypeDTO;
import com.destinyEventScheduler.enums.Status;
import com.destinyEventScheduler.model.Event;
import com.destinyEventScheduler.model.QEntry;
import com.destinyEventScheduler.model.QEvent;
import com.destinyEventScheduler.model.QGame;
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
				.where(qEntry.member.membership.eq(membership).and(qEntry.game.status.eq(Status.VALIDATED)))
				.groupBy(qEntry.game.event.eventType.id, qEntry.game.event.eventType.name)
				.fetch();
	}
	
	@Override
	public FavoriteEventDTO getFavoriteEventId(Long membership){
		QGame qGame = QGame.game;
		QEvent qEvent = QEvent.event;
		return getQuerydsl().createQuery()
				 .select(Projections.bean(FavoriteEventDTO.class, qEvent, qEvent.id.count().as("timesPlayed")))
				 .from(qEntry)
				 .innerJoin(qEntry.game, qGame)
				 .innerJoin(qGame.event, qEvent)
				 .where(qEntry.member.membership.eq(membership).and(qGame.status.eq(Status.VALIDATED)))
				 .groupBy(qEvent.id)
				 .orderBy(qEvent.id.count().desc())
				 .fetchFirst();
	}
	
}

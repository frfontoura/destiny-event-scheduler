package com.destinyEventScheduler.repository.impl;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.destinyEventScheduler.enums.Status;
import com.destinyEventScheduler.model.Game;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.model.QGame;
import com.destinyEventScheduler.repository.GameCustomRepository;
import com.destinyEventScheduler.repository.expressions.GameExpressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAUpdateClause;

@Repository
public class GameRepositoryImpl extends QueryDslRepositorySupport implements GameCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public GameRepositoryImpl() {
		super(Game.class);
	}

	@Override
	public List<Game> getGames(Member member, Status status, Boolean joined) {
		QGame qGame = QGame.game;
		return from(qGame).where(qGame.creator.clan.eq(member.getClan())
				.and(GameExpressions.status(member, status)
				.and(GameExpressions.joined(member, status, joined)))).fetch();
	}

	@Override
	public void updateGamesStatusWaiting(Member member) {
		QGame qGame = QGame.game;    
		new JPAUpdateClause(entityManager, qGame)
		.where(qGame.id.in(
					JPAExpressions.select(qGame.id)
					.where(qGame.creator.clan.eq(member.getClan())
							.and(qGame.time.lt(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime()))
							.and(qGame.status.eq(Status.NEW))
						)
					.from(qGame)
				))
		.set(qGame.status, Status.WAITING)
		.execute();
	}

}

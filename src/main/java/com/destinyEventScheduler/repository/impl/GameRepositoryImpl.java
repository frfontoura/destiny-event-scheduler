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
import com.destinyEventScheduler.model.QClan;
import com.destinyEventScheduler.model.QGame;
import com.destinyEventScheduler.model.QMember;
import com.destinyEventScheduler.repository.GameCustomRepository;
import com.destinyEventScheduler.repository.expressions.GameExpressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAUpdateClause;

@Repository
public class GameRepositoryImpl extends QueryDslRepositorySupport implements GameCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	private QGame qGame = QGame.game;
	
	public GameRepositoryImpl() {
		super(Game.class);
	}

	@Override
	public List<Game> getGames(Member member, Status status, Boolean joined) {
		return from(qGame).where(qGame.creator.clan.eq(member.getClan())
				.and(GameExpressions.status(member, status)
				.and(GameExpressions.joined(member, joined)))).fetch();
	}

	@Override
	public void updateGamesStatusWaiting() {
		new JPAUpdateClause(entityManager, qGame)
		.where(qGame.id.in(
					JPAExpressions.select(qGame.id)
					.where(qGame.time.lt(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime()).and(qGame.status.eq(Status.NEW)))
					.from(qGame)
				))
		.set(qGame.status, Status.WAITING)
		.execute();
	}

	@Override
	public List<Game> getGamesHistory(Member member) {
		QMember qMember = QMember.member;
		QClan qClan = QClan.clan;
		return from(qGame)
				.innerJoin(qGame.creator, qMember)
				.innerJoin(qMember.clan, qClan)
				.where(qClan.eq(member.getClan())
						.and(qGame.status.eq(Status.VALIDATED))
						.and(qGame.evaluations.any().memberA.eq(member)))
				.fetch();
	}

	@Override
	public List<Game> getGamesDone(Member member) {
		QMember qMember = QMember.member;
		List<Game> games = from(qGame)
				.innerJoin(qGame.creator, qMember)
				.where(qGame.creator.eq(member).and(qGame.status.eq(Status.WAITING)))
				.fetch();
		
		List<Game> gamesNotEvaluated = from(qGame)
			.where(qGame.status.eq(Status.VALIDATED)
					.and(GameExpressions.joined(member, Boolean.TRUE))
					.and(GameExpressions.evaluated(member, Boolean.FALSE)))
			.fetch();
		
		games.addAll(gamesNotEvaluated);
		
		return games;
	}

}

package com.destinyEventScheduler.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.destinyEventScheduler.enums.Status;
import com.destinyEventScheduler.model.Game;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.model.QGame;
import com.destinyEventScheduler.repository.GameCustomRepository;
import com.destinyEventScheduler.repository.expressions.GameExpressions;

@Repository
public class GameRepositoryImpl extends QueryDslRepositorySupport implements GameCustomRepository {

	public GameRepositoryImpl() {
		super(Game.class);
	}

	@Override
	public List<Game> getGames(Member member, Status status, Boolean joined) {
		QGame qGame = QGame.game;
		return from(qGame).where(GameExpressions.status(status).and(GameExpressions.joined(member, status, joined))).fetch();
	}

}

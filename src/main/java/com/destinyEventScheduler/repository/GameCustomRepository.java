package com.destinyEventScheduler.repository;

import java.util.List;

import com.destinyEventScheduler.enums.Status;
import com.destinyEventScheduler.model.Game;
import com.destinyEventScheduler.model.Member;

public interface GameCustomRepository {
	
	public List<Game> getGames(Member member, Status status, Boolean joined);
	public void updateGamesStatusWaiting();
}

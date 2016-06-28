package com.destinyEventScheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.model.Clan;
import com.destinyEventScheduler.model.Event;
import com.destinyEventScheduler.model.Game;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	public List<Game> getGamesByClan(Long groupId) {
		return gameRepository.getByCreatorClan(new Clan(groupId));
	}

	@Transactional
	public Game createNewGame(Game game) {
		game.setCreator(new Member(4611686018437203239L));
		game.setEvent(new Event(1L));
		return gameRepository.save(game);
	}
	
}

package com.destinyEventScheduler.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.enums.Status;
import com.destinyEventScheduler.model.Entry;
import com.destinyEventScheduler.model.Game;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private MemberService memberService;
	
	public Game getGameById(Long gameId){
		return gameRepository.findOne(gameId);
	}
	
	@Transactional
	public Game createNewGame(Long membership, Game game) {
		game.setCreator(new Member(membership));
		game.setStatus(Status.NEW);
		game.setEntries(Arrays.asList(createEntry(game.getCreator(), game)));
		return gameRepository.save(game);
	}

	public List<Game> getGamesByStatus(Long membership, Status status) {
		Member member = memberService.getByMembership(membership);
		List<Game> games = null;
		if(member != null){
			games = gameRepository.getGames(membership, member.getClan().getGroupId(), status);
		}
		return games;
	}

	@Transactional
	public void joinGame(Long membership, Long gameId) {
		Member member = memberService.getByMembership(membership);
		Game game = getGameById(gameId);
		if(game.getEntries().size() <= 5 && !game.hasMemberEntry(member)){
			Entry entry = createEntry(member, game);
			game.getEntries().add(entry);
			gameRepository.save(game);
		}
	}
	
	@Transactional
	public void leaveGame(Long membership, Long gameId) {
		Game game = getGameById(gameId);
		Entry entry = game.getEntryByMember(new Member(membership));
		if(entry != null){
			game.getEntries().remove(entry);
			entry.setGame(null);
			gameRepository.save(game);
		}
	}
	
	@Transactional
	public void delete(Long membership, Long gameId) {
		Game game = getGameById(gameId);
		if(game.getCreator().equals(new Member(membership))){
			gameRepository.delete(game);
		}
	}
	
	public List<Entry> getGameEntries(Long gameId) {
		Game game = getGameById(gameId);
		return game.getEntries();
	}
	
	private Entry createEntry(Member member, Game game){
		Entry entry = new Entry();
		entry.setGame(game);
		entry.setMember(member);
		entry.setTime(LocalDateTime.now());
		return entry;
	}

}

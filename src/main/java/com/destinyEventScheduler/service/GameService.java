package com.destinyEventScheduler.service;

import java.time.ZoneId;
import java.util.ArrayList;
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
import com.destinyEventScheduler.utils.DateUtil;

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
	public Game createNewGame(Long membership, Game game, ZoneId zoneId) {
		game.setCreator(new Member(membership));
		game.setStatus(Status.NEW);
		game.setEntries(Arrays.asList(createEntry(game.getCreator(), game, zoneId)));
		game.setTime(DateUtil.toUTC(game.getTime(), zoneId));
		return gameRepository.save(game);
	}

	@Transactional
	public List<Game> getGames(Long membership, Status status, Boolean joined, ZoneId zoneId) {
		Member member = memberService.getByMembership(membership);
		gameRepository.updateGamesStatusWaiting(member);
		List<Game> games = null;
		games = gameRepository.getGames(member, status, joined);
		if(games != null){
			games.stream().forEach(g -> {
				g.setMemberJoined(g.hasMemberEntry(member));
				g.setTimeJson(DateUtil.fromUTC(g.getTime(), zoneId));
			});
		}
		return games;
	}

	@Transactional
	public void joinGame(Long membership, Long gameId, ZoneId zoneId) {
		Member member = new Member(membership);
		Game game = getGameById(gameId);
		if(game.getEntries().size() <= 5 && !game.hasMemberEntry(member)){
			Entry entry = createEntry(member, game, zoneId);
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
	
	@Transactional
	public void validateGame(Long gameId, List<Long> memberships) {
		Game game = getGameById(gameId);
		List<Entry> entries = new ArrayList<>(game.getEntries());
		entries.stream().forEach(e -> {
			if(!memberships.contains(e.getMember().getMembership())){
				game.getEntries().remove(e);
			}
		});
		game.setStatus(Status.VALIDATED);
	}
	
	public List<Entry> getGameEntries(Long gameId, ZoneId zoneId) {
		Game game = getGameById(gameId);
		List<Entry> entries = game.getEntries();
		entries.stream().forEach(e -> e.setTime(DateUtil.fromUTC(e.getTime(), zoneId)));
		return entries;
	}
	
	private Entry createEntry(Member member, Game game, ZoneId zoneId){
		Entry entry = new Entry();
		entry.setGame(game);
		entry.setMember(member);
		entry.setTime(DateUtil.toUTC(game.getTime(), zoneId));
		return entry;
	}

}

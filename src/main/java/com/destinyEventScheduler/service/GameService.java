package com.destinyEventScheduler.service;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.enums.Status;
import com.destinyEventScheduler.model.Entry;
import com.destinyEventScheduler.model.Evaluation;
import com.destinyEventScheduler.model.Event;
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
	
	@Autowired
	private EvaluationService evaluationService;
	
	public Game getGameById(Long gameId){
		gameRepository.updateGamesStatusWaiting();
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
		List<Game> games = null;
		Member member = memberService.getByMembership(membership);
		if(member != null){
			gameRepository.updateGamesStatusWaiting();
			games = gameRepository.getGames(member, status, joined);
			if(games != null){
				games.stream().forEach(g -> {
					g.setMemberJoined(g.hasMemberEntry(member));
					g.setTimeJson(DateUtil.fromUTC(g.getTime(), zoneId));
					g.setEvaluated(!g.getEvaluationsByMember(member).isEmpty());
				});
			}
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
	public void validateGameAndAddEvaluations(Long membership, Long gameId, List<Long> confirmedEntries, List<Evaluation> evaluations) {
		Game game = getGameById(gameId);
		if(Status.WAITING.equals(game.getStatus())){
			validateMembers(confirmedEntries, game);
			game.setStatus(Status.VALIDATED);
			gameRepository.save(game);
			evaluationService.addEvaluations(gameId, membership, evaluations);
			updateMembersFavoriteEvent(game);
		}
	}

	public List<Entry> getGameEntries(Long gameId, ZoneId zoneId) {
		Game game = getGameById(gameId);
		List<Entry> entries = game.getEntries();
		entries.stream().forEach(e -> e.setTimeJson(DateUtil.fromUTC(e.getTime(), zoneId)));
		return entries;
	}

	@Deprecated
	@Transactional
	public void updateStatus(Long gameId, Status status) {
		Game game = getGameById(gameId);
		game.setStatus(status);
		gameRepository.save(game);
	}
	
	private Entry createEntry(Member member, Game game, ZoneId zoneId){
		Entry entry = new Entry();
		entry.setGame(game);
		entry.setMember(member);
		entry.setTime(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
		return entry;
	}
	
	private void validateMembers(List<Long> confirmedEntries, Game game) {
		game.getCreator().setGamesCreated(game.getCreator().getGamesCreated() + 1);
		List<Entry> entries = new ArrayList<>(game.getEntries());
		entries.stream().forEach(e -> {
			if(!confirmedEntries.contains(e.getMember().getMembership())){
				game.getEntries().remove(e);
			} else if(!e.getMember().equals(game.getCreator())) {
				e.getMember().setGamesPlayed(e.getMember().getGamesPlayed() + 1);
			}
		});
	}
	
	private void updateMembersFavoriteEvent(Game game) {
		game.getCreator().setGamesCreated(game.getCreator().getGamesCreated() + 1);
		List<Entry> entries = new ArrayList<>(game.getEntries());
		entries.stream().forEach(e -> {
			Member member = e.getMember();
			Event favoriteEvent = memberService.getFavoriteEvent(member.getMembership()).getEvent();
			member.setFavoriteEvent(favoriteEvent);
		});
	}

}

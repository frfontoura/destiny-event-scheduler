package com.destinyEventScheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.enums.Status;
import com.destinyEventScheduler.model.Entry;
import com.destinyEventScheduler.model.Game;
import com.destinyEventScheduler.service.GameService;

@RestController
@RequestMapping(value = "/game")
public class GameController {

	@Autowired
	private GameService gameService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Game> getGames(@RequestHeader("membership") Long membership,@RequestParam(name ="status", defaultValue = "0", required = false) Integer status){
		return gameService.getGamesByStatus(membership, Status.parse(status));
	}

	@RequestMapping(method = RequestMethod.POST)
	public Long createNewGame(@RequestHeader("membership") Long membership, @RequestBody Game game){
		return gameService.createNewGame(membership, game).getId();
	}
	
	@RequestMapping(value = "/{gameId}/join", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void joinGame(@RequestHeader("membership") Long membership, @PathVariable("gameId") Long gameId){
		gameService.joinGame(membership, gameId);
	}
	
	@RequestMapping(value = "/{gameId}/leave", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void leaveGame(@RequestHeader("membership") Long membership, @PathVariable("gameId") Long gameId){
		gameService.leaveGame(membership, gameId);
	}
	
	@RequestMapping(value = "/{gameId}/entries", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Entry> getGameEntries(@PathVariable("gameId") Long gameId){
		return gameService.getGameEntries(gameId);
	}
	
	@RequestMapping(value = "/{gameId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@RequestHeader("membership") Long membership, @PathVariable("gameId") Long gameId){
		gameService.delete(membership, gameId);
	}
	
}

package com.destinyEventScheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.model.Game;
import com.destinyEventScheduler.service.GameService;

@RestController
@RequestMapping(value = "/game")
public class GameController {

	@Autowired
	private GameService gameService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Game> getNewGames(@RequestHeader("membership") Long membership){
		return gameService.getNewGames(membership);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Long createNewGame(@RequestHeader("membership") Long membership, @RequestBody Game game){
		return gameService.createNewGame(membership, game).getId();
	}
	
	@RequestMapping(value = "/{gameId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void enterGame(@RequestHeader("membership") Long membership, @PathVariable("gameId") Long gameId){
		gameService.enterGame(membership, gameId);
	}
	
	@RequestMapping(value = "/{gameId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void leaveGame(@RequestHeader("membership") Long membership, @PathVariable("gameId") Long gameId){
		gameService.leaveGame(membership, gameId);
	}
	
}

package com.destinyEventScheduler.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.enums.Status;
import com.destinyEventScheduler.model.Game;
import com.destinyEventScheduler.service.GameService;

@RestController
@RequestMapping(value = "/game")
public class GameController {

	@Autowired
	private GameService gameService;

	@RequestMapping(value = "/clan/{groupId}", method = RequestMethod.GET)
	public List<Game> getGamesByClan(@PathVariable("groupId") Long groupId){
		return gameService.getGamesByClan(groupId);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Game createNewGame(){
		Game game = new Game();
		game.setLight(100);
		game.setStatus(Status.STATUS_NEW);
		game.setTime(LocalDateTime.now());
		return gameService.createNewGame(game);
	}
}

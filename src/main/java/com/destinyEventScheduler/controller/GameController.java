package com.destinyEventScheduler.controller;

import java.time.ZoneId;
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

import com.destinyEventScheduler.dto.ValidateDTO;
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
	public List<Game> getGames(@RequestHeader("membership") Long membership, @RequestHeader(value = "zoneId", defaultValue = "America/Sao_Paulo") String zoneId, @RequestParam(name ="status", required = false) Integer status, @RequestParam(name ="joined", required = false) Boolean joined){
		return gameService.getGames(membership, Status.parse(status), joined, ZoneId.of(zoneId));
	}

	@RequestMapping(method = RequestMethod.POST)
	public Long createNewGame(@RequestHeader("membership") Long membership, @RequestHeader(value = "zoneId", defaultValue = "America/Sao_Paulo") String zoneId, @RequestBody Game game){
		return gameService.createNewGame(membership, game, ZoneId.of(zoneId)).getId();
	}
	
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public List<Game> getGamesHistory(@RequestHeader("membership") Long membership, @RequestHeader(value = "zoneId", defaultValue = "America/Sao_Paulo") String zoneId){
		return gameService.getGamesHistory(membership, ZoneId.of(zoneId));
	}
	
	@RequestMapping(value = "/{gameId}/join", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void joinGame(@RequestHeader("membership") Long membership, @RequestHeader(value = "zoneId", defaultValue = "America/Sao_Paulo") String zoneId, @PathVariable("gameId") Long gameId){
		gameService.joinGame(membership, gameId, ZoneId.of(zoneId));
	}
	
	@RequestMapping(value = "/{gameId}/leave", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void leaveGame(@RequestHeader("membership") Long membership, @PathVariable("gameId") Long gameId){
		gameService.leaveGame(membership, gameId);
	}
	
	@RequestMapping(value = "/{gameId}/entries", method = RequestMethod.GET)
	public List<Entry> getGameEntries(@RequestHeader(value = "zoneId", defaultValue = "America/Sao_Paulo") String zoneId, @PathVariable("gameId") Long gameId){
		return gameService.getGameEntries(gameId, ZoneId.of(zoneId));
	}
	
	@RequestMapping(value = "/{gameId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@RequestHeader("membership") Long membership, @PathVariable("gameId") Long gameId){
		gameService.delete(membership, gameId);
	}
	
	@RequestMapping(value = "/{gameId}/validate", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void validateGame(@RequestHeader("membership") Long membership, @PathVariable("gameId") Long gameId, @RequestBody ValidateDTO validateDTO){
		gameService.validateGameAndAddEvaluations(membership, gameId, validateDTO.getEntries(), validateDTO.getEvaluations());
	}

	@RequestMapping(value = "/{gameId}/status/{status}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void updateStatus(@PathVariable("gameId") Long gameId, @PathVariable("status") Integer status){
		gameService.updateStatus(gameId, Status.parse(status));
	}

}
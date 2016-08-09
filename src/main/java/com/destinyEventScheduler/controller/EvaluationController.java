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

import com.destinyEventScheduler.model.Evaluation;
import com.destinyEventScheduler.service.EvaluationService;

@RestController
@RequestMapping(value = "/evaluations")
public class EvaluationController {
	
	@Autowired
	private EvaluationService evaluationService;
	
	@RequestMapping(value = "/game/{gameId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addEvaluation(@RequestHeader("membership") Long membership, @PathVariable("gameId") Long gameId, @RequestBody List<Evaluation> evaluations){
		evaluationService.addEvaluations(gameId, membership, evaluations);
	}
	
	@RequestMapping(value = "/game/{gameId}", method = RequestMethod.GET)
	public List<Evaluation> getByGame(@PathVariable("gameId") Long gameId){
		return evaluationService.getByGameId(gameId);
	}

}

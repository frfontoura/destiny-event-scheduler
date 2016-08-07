package com.destinyEventScheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.model.Evaluation;
import com.destinyEventScheduler.service.EvaluationService;

@RestController
@RequestMapping(value = "/evaluations")
public class EvaluationController {
	
	@Autowired
	private EvaluationService evaluationService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Long addEvaluation(@RequestHeader("membership") Long membership, @RequestBody Evaluation evaluation){
		return evaluationService.addEvaluation(membership, evaluation).getId();
	}
	
	@RequestMapping(value = "/game/{gameId}", method = RequestMethod.GET)
	public List<Evaluation> getByGame(@PathVariable("gameId") Long gameId){
		return evaluationService.getByGameId(gameId);
	}

}

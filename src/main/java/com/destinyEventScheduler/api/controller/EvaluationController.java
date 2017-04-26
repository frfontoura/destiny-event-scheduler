package com.destinyEventScheduler.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.dto.MemberHistoryDTO;
import com.destinyEventScheduler.model.Evaluation;
import com.destinyEventScheduler.service.EvaluationService;
import com.destinyEventScheduler.utils.SecurityUtils;

@RestController
@RequestMapping(value = "/api/game/{gameId}")
public class EvaluationController {
	
	@Autowired
	private EvaluationService evaluationService;
	
	@RequestMapping(value = "/evaluations", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addEvaluation(@PathVariable("gameId") Long gameId, @RequestBody List<Evaluation> evaluations){
		evaluationService.addEvaluations(gameId, SecurityUtils.getCurrentMembership(), evaluations);
	}

	@RequestMapping(value = "/evaluations", method = RequestMethod.GET)
	public List<Evaluation> getMemberGameEvaluation(@PathVariable("gameId") Long gameId){
		return evaluationService.getMemberGameEvaluation(SecurityUtils.getCurrentMembership(), gameId);
	}
	
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public List<MemberHistoryDTO> getGameHistory(@PathVariable("gameId") Long gameId){
		return evaluationService.getGameHistory(gameId);
	}
	
}

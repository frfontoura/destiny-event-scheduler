package com.destinyEventScheduler.dto;

import java.util.List;

import com.destinyEventScheduler.model.Evaluation;

public class ValidateDTO {

	private List<Long> entries;
	private List<Evaluation> evaluations;

	public List<Long> getEntries() {
		return entries;
	}

	public void setEntries(List<Long> memberships) {
		this.entries = memberships;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

}

package com.destinyEventScheduler.dto;

import java.util.List;

import com.destinyEventScheduler.model.Evaluation;

public class ValidateDTO {

	private List<String> entries;
	private List<Evaluation> evaluations;

	public List<String> getEntries() {
		return entries;
	}

	public void setEntries(List<String> memberships) {
		this.entries = memberships;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

}

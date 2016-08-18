package com.destinyEventScheduler.repository;

import java.util.List;

import com.destinyEventScheduler.dto.MemberHistoryDTO;

public interface EvaluationCustomRepository {

	public List<MemberHistoryDTO> getGameHistory(Long gameId);
}

package com.destinyEventScheduler.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.destinyEventScheduler.enums.Status;
import com.destinyEventScheduler.model.Clan;
import com.destinyEventScheduler.model.Game;

public interface GameRepository extends CrudRepository<Game, Long> {

	public List<Game> getByCreatorClanAndStatusOrderByTime(Clan clan, Status status);

}

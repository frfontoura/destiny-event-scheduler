package com.destinyEventScheduler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.destinyEventScheduler.enums.Status;
import com.destinyEventScheduler.model.Game;

public interface GameRepository extends CrudRepository<Game, Long> {

	@Query("Select g from Game g JOIN g.creator m JOIN m.clan c where c.groupId = :groupId and g.status = :status and m.membership <> :membership")
	public List<Game> getGames(@Param("membership") Long membership, @Param("groupId") Long groupId, @Param("status") Status status);
}

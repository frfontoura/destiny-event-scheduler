package com.destinyEventScheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.destinyEventScheduler.model.Clan;

@Repository
public interface ClanRepository extends CrudRepository<Clan, Long> {

}

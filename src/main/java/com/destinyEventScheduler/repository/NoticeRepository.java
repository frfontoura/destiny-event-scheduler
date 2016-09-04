package com.destinyEventScheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.destinyEventScheduler.model.Notice;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

	public Notice findTopByOrderByIdDesc();
	
}

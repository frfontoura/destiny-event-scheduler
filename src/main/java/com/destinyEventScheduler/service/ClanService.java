package com.destinyEventScheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.model.Clan;
import com.destinyEventScheduler.repository.ClanRepository;

@Service
public class ClanService {

	@Autowired
	private ClanRepository clanRepository;
	
	public Clan getByGroupId(Long groupId){
		return clanRepository.findOne(groupId);
	}
	
}

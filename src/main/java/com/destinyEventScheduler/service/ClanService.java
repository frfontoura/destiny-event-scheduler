package com.destinyEventScheduler.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.model.Clan;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.repository.ClanRepository;

@Service
public class ClanService {

	@Autowired
	private ClanRepository clanRepository;
	
	public Clan getByGroupId(Long groupId){
		return clanRepository.findOne(groupId);
	}

	public List<String> getClanMembersByGroupId(Long groupId) {
		Clan clan = getByGroupId(groupId);
		List<String> members = clan.getMembers().stream()
				.map(Member::getMembershipJson)
				.collect(Collectors.toCollection(ArrayList::new));
		return members;
	}
	
}

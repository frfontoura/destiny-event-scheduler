package com.destinyEventScheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.dto.MemberProfileDTO;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.repository.EntryRepository;
import com.destinyEventScheduler.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private EventService eventService;
	
	public Member getByMembership(Long membership){
		return memberRepository.findOne(membership);
	}

	@Transactional
	public void save(Member member) {
		memberRepository.save(member);
	}

	public Iterable<Member> getMembersByIds(List<Long> membersIds) {
		return memberRepository.findAll(membersIds);
	}

	public MemberProfileDTO getMemberProfile(Long membership) {
		Member member = getByMembership(membership);
		MemberProfileDTO memberProfileDTO = new MemberProfileDTO();
		memberProfileDTO.setMember(member);
		memberProfileDTO.setEvaluationsMade(member.getEvaluationsA().size());
		memberProfileDTO.setPlayedTypes(entryRepository.getEventTypeCountByMember(membership));
		memberProfileDTO.setFavoriteEvent(eventService.getById(entryRepository.getFavoriteEventId(membership)));
		return memberProfileDTO;
	}
	
}

package com.destinyEventScheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public Member getByMembership(Long membership){
		return memberRepository.findOne(membership);
	}

	@Transactional
	public void save(Member member) {
		memberRepository.save(member);
	}

	public List<Member> getMembersByIds(List<Long> membersIds) {
		return memberRepository.findAll(membersIds);
	}
	
}

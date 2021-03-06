package com.destinyEventScheduler.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.dto.MemberProfileDTO;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.service.MemberService;
import com.destinyEventScheduler.utils.SecurityUtils;

@RestController
@RequestMapping(value = "/api/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/{membership}", method = RequestMethod.GET)
	public Member getMember(@PathVariable("membership") Long membership){
		return memberService.getByMembership(membership);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Member getCurrentMember(){
		return SecurityUtils.getCurrentMember();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Iterable<Member> getMembers(@RequestBody List<Long> membersIds){
		return memberService.getMembersByIds(membersIds);
	}
	
	@RequestMapping(value = "/{membership}/profile", method = RequestMethod.GET)
	public MemberProfileDTO getMemberProfile(@PathVariable("membership") Long membership){
		return memberService.getMemberProfile(membership);
	}
}

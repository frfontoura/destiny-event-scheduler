package com.destinyEventScheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.jsonview.MemberView;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.service.MemberService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/{membership}", method = RequestMethod.GET)
	@JsonView({MemberView.MemberBasic.class})
	public Member getMember(@PathVariable("membership") Long membership){
		return memberService.getByMembership(membership);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@JsonView({MemberView.MemberBasic.class})
	public Member getCurrentMember(@RequestHeader("membership") Long membership){
		return memberService.getByMembership(membership);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@JsonView({MemberView.MemberBasic.class})
	public List<Member> getMembers(@RequestBody List<Long> membersIds){
		return memberService.getMembersByIds(membersIds);
	}
}

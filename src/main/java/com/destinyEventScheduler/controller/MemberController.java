package com.destinyEventScheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.service.MemberService;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/{membership}", method = RequestMethod.GET)
	public Member getMember(@PathVariable("membership") Long membership){
		return memberService.getByMembership(membership);
	}
	
}

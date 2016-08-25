package com.destinyEventScheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.enums.Platform;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.service.LoginService;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.OK)
	public Member login(@RequestHeader("membership") Long membership, @RequestHeader("platform") Integer platform, @RequestHeader("clanId") Long clanId){
		return loginService.login(membership, Platform.parse(platform), clanId);
	}

}

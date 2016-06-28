package com.destinyEventScheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.dto.bungie.membersOfClan.MemberBungie;
import com.destinyEventScheduler.enums.Platform;
import com.destinyEventScheduler.service.LoginService;
import com.destinyEventScheduler.service.bungie.BungieApiService;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private BungieApiService bungieApiService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.OK)
	public void login(@RequestHeader("membership") Long membership, @RequestHeader("platform") Integer platform){
		loginService.login(membership, Platform.parse(platform));
	}
	
	@RequestMapping(value = "/teste", method = RequestMethod.GET)
	@ResponseBody
	public List<MemberBungie> run() throws Exception {
        return bungieApiService.getMembersOfClan("548691", Platform.PSN, 1);
	}
}

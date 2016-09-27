package com.destinyEventScheduler.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.model.LogApp;
import com.destinyEventScheduler.service.LogAppService;

@RestController
@RequestMapping(value = "/api/log-app")
public class LogAppController {

	@Autowired
	private LogAppService logAppService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<LogApp> getLogs(){
		return logAppService.getLogs();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void validateGame(@RequestHeader("membership") Long membership, @RequestBody LogApp logApp){
		logAppService.addLog(membership, logApp);
	}
	
}

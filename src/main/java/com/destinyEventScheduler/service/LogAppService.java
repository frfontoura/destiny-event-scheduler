package com.destinyEventScheduler.service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.model.LogApp;
import com.destinyEventScheduler.repository.LogAppRepository;

@Service
public class LogAppService {

	@Autowired
	private LogAppRepository logAppRepository;
	
	@Transactional
	public void addLog(Long membership, LogApp logApp){
		logApp.setMembership(membership);
		logApp.setTime(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
		logAppRepository.save(logApp);
	}
	
	public Iterable<LogApp> getLogs(){
		return logAppRepository.findAll();
	}
	
}

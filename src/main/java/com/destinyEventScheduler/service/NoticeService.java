package com.destinyEventScheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.model.Notice;
import com.destinyEventScheduler.repository.NoticeRepository;

@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	public Iterable<Notice> getAll(){
		return noticeRepository.findAll();
	}
	
}

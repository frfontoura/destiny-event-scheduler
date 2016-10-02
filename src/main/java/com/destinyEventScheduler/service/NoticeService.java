package com.destinyEventScheduler.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.model.Notice;
import com.destinyEventScheduler.repository.NoticeRepository;

@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	public Notice getLastNotice(){
		return noticeRepository.findTopByOrderByIdDesc();
	}

	@Transactional
	public Notice createNewNotice(Notice notice) {
		return noticeRepository.save(notice);
	}
	
}

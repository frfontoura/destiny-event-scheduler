package com.destinyEventScheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.model.Notice;
import com.destinyEventScheduler.service.NoticeService;

@RestController
@RequestMapping(value = "/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Notice getNotices(){
		return noticeService.getAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Long createNewNotice(@RequestBody Notice notice){
		return noticeService.createNewNotice(notice).getId();
	}
	
}
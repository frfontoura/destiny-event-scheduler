package com.destinyEventScheduler.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.destinyEventScheduler.enums.Platform;
import com.destinyEventScheduler.model.Clan;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.service.ClanService;

@RestController
@RequestMapping(value = "/api/clan")
public class ClanController {

    @Autowired
    private ClanService clanService;
    
    @PersistenceContext
    private EntityManager em;
    
    
    @RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
    public Clan getClan(@PathVariable("groupId") Long groupId){
        return clanService.getByGroupId(groupId);
    }
    
    @RequestMapping(value = "/{groupId}/members", method = RequestMethod.GET)
    public List<String> getClanMembersId(@RequestHeader("membership") Long membership, @RequestHeader("platform") Integer platform, @PathVariable("groupId") Long groupId){
        return clanService.getClanMembersByGroupId(membership, Platform.parse(platform), groupId);
    }
    
    @RequestMapping(value = "/{groupId}/members-full", method = RequestMethod.GET)
    public List<Member> getClanMembers(@PathVariable("groupId") Long groupId){
        return clanService.getByGroupId(groupId).getMembers();
    }
    
}
package com.destinyEventScheduler.factory;

import com.destinyEventScheduler.dto.MemberHistoryDTO;
import com.destinyEventScheduler.model.Member;

public abstract class MemberHistoryDTOFactory {

	public static MemberHistoryDTO createMemberHistoryDTO(Member member) {
		MemberHistoryDTO memberHistoryDTO = new MemberHistoryDTO();
		memberHistoryDTO.setMembership(member.getMembership());
		memberHistoryDTO.setName(member.getName());
		memberHistoryDTO.setIcon(member.getIcon());
		memberHistoryDTO.setTotalLikes(0);
		memberHistoryDTO.setTotalDislikes(0);
		memberHistoryDTO.setMemberTitle(member.getMemberTitle());
		return memberHistoryDTO;
	}
	
}

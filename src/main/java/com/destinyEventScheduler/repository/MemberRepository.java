package com.destinyEventScheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.destinyEventScheduler.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}

package com.destinyEventScheduler.repository;

import org.springframework.data.repository.CrudRepository;

import com.destinyEventScheduler.model.Member;

public interface MemberRepository extends CrudRepository<Member, Long>{

}

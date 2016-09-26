package com.destinyEventScheduler.configuration.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.destinyEventScheduler.model.Member;

import java.util.Collection;

public class AuthenticatedUser implements Authentication {

	private static final long serialVersionUID = 1L;

	private boolean authenticated = true;
	private Member member;
	
	public AuthenticatedUser(Member member) {
		this.member = member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return member.getRoles();
	}

	@Override
	public Object getCredentials() {
		return "";
	}

	@Override
	public Object getDetails() {
		return "";
	}

	@Override
	public Object getPrincipal() {
		return member;
	}

	@Override
	public boolean isAuthenticated() {
		return this.authenticated;
	}

	@Override
	public void setAuthenticated(boolean b) throws IllegalArgumentException {
		this.authenticated = b;
	}

	@Override
	public String getName() {
		return this.member.getMembershipJson();
	}
}
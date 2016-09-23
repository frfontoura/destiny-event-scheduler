package com.destinyEventScheduler.configuration.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.destinyEventScheduler.configuration.security.WebSecurityConfig;
import com.destinyEventScheduler.enums.Platform;
import com.destinyEventScheduler.service.LoginService;
import com.google.common.primitives.Longs;

@Component
public class DestinySchedulerLoginFilter extends OncePerRequestFilter {

	@Autowired
	private LoginService loginService;

	private RequestMatcher myMatcher = new AntPathRequestMatcher(WebSecurityConfig.LOGIN_PATH);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)	throws ServletException, IOException {
		if (myMatcher.matches(request)) {
			validateRequest(request);
			
			Long membership = Longs.tryParse(request.getHeader("membership"));
			Long clanId = Longs.tryParse(request.getHeader("clanId"));
			Platform platform = Platform.parse(request.getHeader("platform"));
			
			loginService.login(membership, platform, clanId);
		}
		filterChain.doFilter(request, response);
	}

	private void validateRequest(HttpServletRequest request) {
		if(request.getHeader("membership") == null || request.getHeader("clanId") == null || request.getHeader("platform") == null){
			throw new IllegalArgumentException("membership, clanId and platform can't be null");
		}
	}

}
package com.destinyEventScheduler.configuration.security.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.destinyEventScheduler.service.MemberService;
import com.google.common.primitives.Longs;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenAuthenticationService {

	private long EXPIRATION_TIME = 1000 * 60 * 15;
	private String headerString = "Authorization";

	@Autowired
	private MemberService memberService;
	
	@Value("${jwt.secret}")
	private String secret;

	public void addAuthentication(HttpServletResponse response, String membership) {
		String JWT = Jwts.builder()
				.setSubject(membership)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		response.addHeader(headerString, JWT);
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(headerString);
		if (token != null) {
			String membership = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
			if (membership != null) {
				return new AuthenticatedUser(memberService.getByMembership(Longs.tryParse(membership)));
			}
		}
		return null;
	}
}
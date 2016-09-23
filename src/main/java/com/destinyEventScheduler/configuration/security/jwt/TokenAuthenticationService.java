package com.destinyEventScheduler.configuration.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TokenAuthenticationService {

	private long EXPIRATIONTIME = 1000 * 60 * 10;//60 * 24 * 10;
	private String headerString = "Authorization";

	@Value("${jwt.secret}")
	private String secret;

	public void addAuthentication(HttpServletResponse response, String membership) {
		String JWT = Jwts.builder().setSubject(membership)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		response.addHeader(headerString, JWT);
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(headerString);
		if (token != null) {
			String membership = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
			if (membership != null) {
				return new AuthenticatedUser(membership);
			}
		}
		return null;
	}
}
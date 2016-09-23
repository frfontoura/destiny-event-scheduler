package com.destinyEventScheduler.configuration.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.destinyEventScheduler.configuration.security.jwt.DestinySchedulerLoginFilter;
import com.destinyEventScheduler.configuration.security.jwt.JWTAuthenticationFilter;
import com.destinyEventScheduler.configuration.security.jwt.JWTLoginFilter;
import com.destinyEventScheduler.configuration.security.jwt.TokenAuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String LOGIN_PATH = "/login";
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private DestinySchedulerLoginFilter dsLoginFilter;
	
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().cacheControl();

		http.csrf().disable() 
			.authorizeRequests().antMatchers("/")
			.permitAll().antMatchers(HttpMethod.POST, LOGIN_PATH).permitAll()
			.anyRequest().authenticated().and()
			.addFilterBefore(dsLoginFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JWTLoginFilter(LOGIN_PATH, authenticationManager(), tokenAuthenticationService),	UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JWTAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(getUserQuery())
			.authoritiesByUsernameQuery(getAuthoritiesQuery());
	}

	private String getUserQuery() {
		return "SELECT CAST(membership as character varying) as username, password as password, 1 as enabled FROM member WHERE CAST(membership as character varying) = ?";
	}

	private String getAuthoritiesQuery() {
		return "select distinct CAST(m.membership as character varying), r.role from member m inner join member_roles mr on m.membership = mr.member_id inner join roles r on r.id = mr.role_id where CAST(m.membership as character varying) = ?";
	}

}
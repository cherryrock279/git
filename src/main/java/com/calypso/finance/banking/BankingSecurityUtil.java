package com.calypso.finance.banking;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class BankingSecurityUtil extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("lei").password("22").roles("USER").
		and().withUser("jo").password("jo").roles("USER","ADMIN");
		//auth.jdbcAuthentication().withUser("qiao").password("22").roles("ADMIN");
		}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.POST,"/name")
		.hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/person").hasRole("USER")
		.antMatchers(HttpMethod.PUT,"/person").hasRole("ADMIN").and()
		.csrf().disable();
	}
}

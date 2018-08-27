package com.aitechwizards.secure.sceurity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BasicSecurity extends WebSecurityConfigurerAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(BasicSecurity.class);

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		LOG.info("INVOKED : configure()...");
		auth.inMemoryAuthentication().withUser("Bharat").password("singh").roles("ADMIN").and().withUser("abc")
				.password("abc").roles("ADMIN", "USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOG.info("INVOKED : configure()");
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}

}

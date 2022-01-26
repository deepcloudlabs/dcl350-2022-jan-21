package com.example.hr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.hr.filter.JwtTokenFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Value("${security.jwt.token.secret-key}")
	private String secret;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("configurating security...");
		http.csrf().disable().authorizeRequests()
		        .antMatchers("/api/v1/login","/api/v1/csrf/**","/api/v1/v2/api-docs/**","/api/v1/webjars/**","/api/v1/swagger-ui.html/**","/api/v1/swagger-ui.html/swagger-resources/**","/api/v1/swagger-resources/**","/api/v1/swagger-ui.html/swagger-resources/configuration/security/**","/api/v1/swagger-ui.html/swagger-resources/configuration/ui/**")
				.permitAll().anyRequest().authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(new JwtTokenFilter(userDetailsService, secret),
				UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withUsername("jack").password("{noop}secret").roles("USER").build();

		return new InMemoryUserDetailsManager(user);
	}
}

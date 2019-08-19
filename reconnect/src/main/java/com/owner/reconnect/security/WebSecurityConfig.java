package com.owner.reconnect.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.owner.reconnect.auth.handler.failure.CustomAuthenticationFailureHandler;
import com.owner.reconnect.auth.handler.logout.CustomLogoutSuccessHandler;
import com.owner.reconnect.auth.handler.success.CustomAuthenticationSuccessHandler;
import com.owner.reconnect.user.service.impl.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
@EnableWebMvc
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/user/register");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers("/login", "/register").permitAll().and()
				.formLogin().loginPage("/login").permitAll()
				.successHandler(customAuthenticationSuccessHandler())
				.failureHandler(customAuthenticationFailureHandler()).and()
				.logout().logoutSuccessHandler(customLogoutSuccessHandler())
				.permitAll();
		// http.sessionManagement().sessionCreationPolicy(ALWAYS).and()
		// .exceptionHandling().and().cors().and().csrf().disable()
		// .authorizeRequests().antMatchers("/login", "/register")
		// .permitAll().anyRequest().authenticated().and().formLogin()
		// .loginPage("/login").permitAll()
		// .successHandler(customAuthenticationSuccessHandler())
		// .failureHandler(customAuthenticationFailureHandler()).and()
		// .logout().logoutSuccessHandler(customLogoutSuccessHandler())
		// .permitAll();
		// http.headers().frameOptions().disable();

	}

	@Bean
	public AuthenticationFailureHandler customAuthenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

	@Bean
	public CustomLogoutSuccessHandler customLogoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}

	@Bean
	public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}

}

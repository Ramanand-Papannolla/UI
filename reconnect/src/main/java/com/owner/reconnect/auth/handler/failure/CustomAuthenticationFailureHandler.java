package com.owner.reconnect.auth.handler.failure;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements
		AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		// response.setStatus(HttpStatus.UNAUTHORIZED.value());
		// Map<String, Object> data = new HashMap<>();
		// data.put("timestamp", LocalDateTime.now());
		// data.put("exception", exception.getMessage());
		throw exception;
	}
}

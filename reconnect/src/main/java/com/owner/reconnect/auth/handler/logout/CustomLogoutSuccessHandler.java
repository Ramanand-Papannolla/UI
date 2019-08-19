package com.owner.reconnect.auth.handler.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		if (session != null) {
			session.removeAttribute("user");
			if (authentication != null && authentication.getDetails() != null) {
				try {
					session.invalidate();
					System.out.println("User Successfully Logout");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		response.setStatus(HttpServletResponse.SC_OK);
	}

}

package com.owner.reconnect.auth.handler.success;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.owner.reconnect.dto.mapper.UserDetailsMapper;
import com.owner.reconnect.loginout.ActiveUserStore;
import com.owner.reconnect.loginout.LoggedUser;

public class CustomAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {
	// private RedirectStrategy redirectStrategy = new
	// DefaultRedirectStrategy();

	@Autowired
	private UserDetailsMapper userDetailsMapper;

	@Autowired
	ActiveUserStore activeUserStore;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			LoggedUser user = new LoggedUser(authentication.getName(),
					activeUserStore);
			session.setAttribute("user", user);
			// UserDetails authUser = (UserDetails) SecurityContextHolder
			// .getContext().getAuthentication().getPrincipal();
			// session.setAttribute("username", authUser.getUsername());
			// session.setAttribute("authorities",
			// authentication.getAuthorities());
		}
		// HttpSession session = request.getSession();

		// response.getOutputStream().print(
		// new ObjectMapper().writeValueAsString(userDetailsDto));
		request.getRequestDispatcher("/login").forward(request, response);
	}

}

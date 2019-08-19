package com.owner.reconnect.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.owner.reconnect.entities.Roles;
import com.owner.reconnect.loginout.ActiveUserStore;
import com.owner.reconnect.repository.UserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	ActiveUserStore activeUserStore;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		if (activeUserStore.getUsers().contains(username.trim())) {

		}
		com.owner.reconnect.entities.UserDetails userDetails = this.userRepository
				.findByEmailId(username);
		if (null == userDetails) {
			throw new UsernameNotFoundException("Username Not found: "
					+ username);
		}
		List<GrantedAuthority> authorities = getAuthorities(userDetails);
		userDetails.authorities = authorities;
		return userDetails;
	}

	private List<GrantedAuthority> getAuthorities(
			com.owner.reconnect.entities.UserDetails userDetails) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Roles role : userDetails.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}

}

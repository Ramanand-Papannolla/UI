package com.owner.reconnect.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NameNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.owner.reconnect.dto.UserDetailsDto;
import com.owner.reconnect.dto.mapper.UserDetailsMapper;
import com.owner.reconnect.entities.Roles;
import com.owner.reconnect.entities.UserDetails;
import com.owner.reconnect.exception.UsernameAlreadyUsedException;
import com.owner.reconnect.repository.RoleRepository;
import com.owner.reconnect.repository.UserRepository;
import com.owner.reconnect.role.Role;
import com.owner.reconnect.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userDao;

	@Autowired
	private UserDetailsMapper userDetailsMapper;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetailsDto connect() throws UsernameAlreadyUsedException {
		UserDetails authUser = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (authUser.getConnected()) {
			throw new UsernameAlreadyUsedException(
					"This user is already connected: " + authUser.getUserName());
		}
		authUser.setConnected(true);
		UserDetailsDto userDetailsDto = this.userDetailsMapper
				.mapToUserDetailsDto(authUser);
		return userDetailsDto;
	}

	@Override
	public UserDetailsDto disconnect(Long userId) throws NameNotFoundException {
		if (userId == null) {
			return null;
		}
		UserDetails dbUser = userDao.findById(userId);
		if (dbUser != null) {
			dbUser.setConnected(false);
			UserDetailsDto disconnectedUser = this.userDetailsMapper
					.mapToUserDetailsDto(dbUser);
			return disconnectedUser;
		}
		throw new NameNotFoundException("User not found " + userId);

	}

	@Override
	public UserDetailsDto registerUser(UserDetailsDto userDetailsDto) {
		UserDetails userDetails = this.userDetailsMapper
				.mapToUserDetails(userDetailsDto);
		Roles role = roleRepository.findUserRole(Role.USER);
		Set<Roles> roles = new HashSet<>();
		roles.add(role);
		userDetails.setRoles(roles);
		UserDetails registerUser = userDao.registerUser(userDetails);
		UserDetailsDto registerUserDto = this.userDetailsMapper
				.mapToUserDetailsDto(registerUser);
		return registerUserDto;

	}
	public String generateBCryptPassword(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	public List<String> getRoles(
			Collection<? extends GrantedAuthority> authorities) {
		List<String> roles = new ArrayList<>();
		for (GrantedAuthority grantedAuthority : authorities) {
			roles.add(grantedAuthority.getAuthority());
		}
		return roles;
	}

	@Override
	public List<UserDetailsDto> findAll() {
		List<UserDetails> userDetails = userDao.findAll();
		List<UserDetailsDto> userDetailsDtos = this.userDetailsMapper
				.mapToUserDetailsDtos(userDetails);
		return userDetailsDtos;
	}

}

package com.owner.reconnect.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.owner.reconnect.dto.UserDetailsDto;
import com.owner.reconnect.exception.UsernameAlreadyUsedException;
import com.owner.reconnect.repository.UserRepository;
import com.owner.reconnect.service.UserService;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userDao;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDetailsDto login(HttpServletRequest request,
			HttpServletResponse response) throws UsernameAlreadyUsedException {
		UserDetailsDto userDetailsDto = userService.connect();
		return userDetailsDto;
	}


	@RequestMapping(value = "/listUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<UserDetailsDto> findConnectedUsers() {
		return userService.findAll();
	}

	@RequestMapping(value = "/clearAll", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void clearAll() {
		userDao.deleteAll();
	}
}

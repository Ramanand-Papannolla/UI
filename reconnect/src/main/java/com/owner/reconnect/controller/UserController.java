package com.owner.reconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.owner.reconnect.dto.UserDetailsDto;
import com.owner.reconnect.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public UserDetailsDto userRegistration(@RequestBody UserDetailsDto user) {
		UserDetailsDto registerUser = userService.registerUser(user);
		return registerUser;
	}

}

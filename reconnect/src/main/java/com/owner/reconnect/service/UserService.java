package com.owner.reconnect.service;

import java.util.List;

import javax.naming.NameNotFoundException;

import com.owner.reconnect.dto.UserDetailsDto;
import com.owner.reconnect.exception.UsernameAlreadyUsedException;

public interface UserService {

	/**
	 * <p>
	 * Find the user by its user name. If the user is not saved yet, then save
	 * the user into database, otherwise throw a
	 * {@link UsernameAlreadyUsedException}
	 * </p>
	 * 
	 * @param user
	 * @return The connected user
	 * @throws UsernameAlreadyUsedException
	 */
	UserDetailsDto connect() throws UsernameAlreadyUsedException;

	/**
	 * <p>
	 * Remove the user from the database.
	 * </p>
	 * 
	 * @param user
	 */
	UserDetailsDto disconnect(Long userId) throws NameNotFoundException;

	UserDetailsDto registerUser(UserDetailsDto user);

	List<UserDetailsDto> findAll();

}

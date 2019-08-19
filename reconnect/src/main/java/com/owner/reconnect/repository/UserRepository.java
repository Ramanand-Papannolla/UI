package com.owner.reconnect.repository;

import java.util.List;

import com.owner.reconnect.entities.UserDetails;

public interface UserRepository extends UserRepositoryCustom {

	UserDetails findById(Long userId);

	// UserDetails findByName(String name);
	// @Transactional
	// Long deleteByName(String name);
	UserDetails findByEmailId(String emailid);

	List<UserDetails> findAll();

	void deleteAll();

}

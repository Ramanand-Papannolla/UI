package com.owner.reconnect.repository;

import com.owner.reconnect.entities.Roles;
import com.owner.reconnect.role.Role;

public interface RoleRepository {
	Roles findUserRole(Role role);
}

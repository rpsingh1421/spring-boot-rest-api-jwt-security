package com.restapi.adminBackend.services;

import java.util.List;

import com.restapi.adminBackend.entities.Role;

public interface RoleService {
	Role addRole(Role role);
	Role updateRole(Role role, long roleId);
	List<Role> getRoles();
	Role getRoleById(Long roleId);
	void deleteRole(Long roleId);
}

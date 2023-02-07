package com.restapi.adminBackend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.adminBackend.dao.RoleDao;
import com.restapi.adminBackend.entities.Role;
import com.restapi.adminBackend.exceptions.customExceptions.ResourceNotFoundException;
import com.restapi.adminBackend.services.RoleService;

@Service
public class RoleServiceimpl implements RoleService{
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Role addRole(Role role) {
		// TODO Auto-generated method stub
		Role addedRole = roleDao.save(role);
		return addedRole;
	}

	@Override
	public Role updateRole(Role role, long roleId) {
		Role existRole = this.roleDao.findById(roleId).orElseThrow(()-> new ResourceNotFoundException("Role","roleName",roleId));
		
		Role updatedRole = this.roleDao.save(role);
		return updatedRole;
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return this.roleDao.findAll();
	}

	@Override
	public Role getRoleById(Long roleId) {
		Role existRole = this.roleDao.findById(roleId).orElseThrow(()-> new ResourceNotFoundException("Role","roleName",roleId));
		
		return existRole;
	}

	@Override
	public void deleteRole(Long roleId) {
		Role existRole = this.roleDao.findById(roleId).orElseThrow(()-> new ResourceNotFoundException("Role","roleName",roleId));
		this.deleteRole(roleId);
		
	}
	
}

package com.restapi.adminBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.adminBackend.entities.Role;
import com.restapi.adminBackend.services.RoleService;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/roles")
	public List<Role> getRoles(){
		return roleService.getRoles();
	}
	
	@GetMapping("/role/{id}")
	public Role getRole(@PathVariable ("id") long roleId)
	{
		return roleService.getRoleById(roleId);
	}
	@PostMapping("/add_role")
	public void addRole(@RequestBody Role role)
	{
		roleService.addRole(role);
	}
	@PutMapping("/update_role/{id}")
	public void updateRole(@RequestBody Role role, @PathVariable ("id") long roleId)
	{
		roleService.updateRole(role, roleId);
	}
	@DeleteMapping("/delete_role/{id}")
	public void deleteRole(@PathVariable ("id") long roleId)
	{
		roleService.deleteRole(roleId);
	}
	
}

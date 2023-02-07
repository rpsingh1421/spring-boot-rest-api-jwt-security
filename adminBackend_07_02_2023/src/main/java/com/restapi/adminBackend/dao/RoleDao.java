package com.restapi.adminBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.adminBackend.entities.Role;

public interface RoleDao extends JpaRepository<Role, Long>{

}

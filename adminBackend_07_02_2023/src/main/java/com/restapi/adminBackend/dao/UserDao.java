package com.restapi.adminBackend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restapi.adminBackend.entities.User;

public interface UserDao extends JpaRepository<User, Long>{
	
	
	
	//@Query(from tablename(if tablename is same as  entity class name)/entity name/ or entity classname where lower(column_name) = lower(:param variable) order by column_name desc/asec)
	//: is used to denote the value associated with param variable name
	//lower()  is used change string into lowercase bcz query is case sensitive
//	@Query(value = "select id from user_credentials ORDER BY id DESC LIMIT 1",  nativeQuery = true)
//	public String getLastId();
	
	@Query(value = "select * from user_credentials ORDER BY id DESC LIMIT 1",  nativeQuery = true)
	public User getLastUser();
	
	@Query(value="select role_id from user_credentials where id=?1 ",nativeQuery =true )
	public long findRole(long id);
	
	Optional<User> findByEmail(String email);
	

}

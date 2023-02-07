package com.restapi.adminBackend.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.restapi.adminBackend.dao.UserDao;
import com.restapi.adminBackend.entities.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Loading User from DataBase by email
//		 Optional<User> user = userDao.findByEmail(username);
//	        return user.map(CustomUserDetails::new)
//	                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
		
//		User user = userDao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
		User user = userDao.findByEmail(username).orElseThrow();
		return new CustomUserDetails(user);
	}

}

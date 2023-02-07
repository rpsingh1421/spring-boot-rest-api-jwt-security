package com.restapi.adminBackend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.restapi.adminBackend.config.UserDetailsServiceImpl;
import com.restapi.adminBackend.dao.UserDao;
import com.restapi.adminBackend.jwt.JwtUtils;

@Service
public class AuthenticationService {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private JwtUtils jwtUtils;
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
	    authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(
	            request.getEmail(),
	            request.getPassword()
	        )
	    );
//	    UserDetails user = userDao.findByEmail(request.getEmail())
//		        .orElseThrow();
	    UserDetails user = this.userDetailsService.loadUserByUsername(request.getEmail());;
	    var jwtToken = jwtUtils.generateToken(user);
	    return AuthenticationResponse.builder()
	        .token(jwtToken)
	        .build();
	  }
}

package com.restapi.adminBackend.auth;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restapi.adminBackend.config.UserDetailsServiceImpl;
import com.restapi.adminBackend.jwt.JwtUtils;


@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthenticationController {
	 
	@Autowired
	private AuthenticationService authenticationService;
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	@Autowired
//	private UserDetailsServiceImpl userDetailsService;
//	@Autowired
//	private JwtUtils jwtUtils;
	@PostMapping("/generate-token")
//	  public ResponseEntity<?> generateToken( @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//	    try {
//	    	authenticate(authenticationRequest.getEmail(),authenticationRequest.getPassword());
//	    }catch(Exception e){
//	    	e.printStackTrace();
//	    	throw new Exception("User Not Found");
//	    }
//	    ///////authenticate
//	    
//	    UserDetails userDetails=this.userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//	    String token = this.jwtUtils.generateToken(userDetails);
//	    return ResponseEntity.ok(new AuthenticationResponse(token));
	    
//	}
	public ResponseEntity<AuthenticationResponse> authenticate(
		      @RequestBody AuthenticationRequest request
		  ) {
		    return ResponseEntity.ok(authenticationService.authenticate(request));
		  }
	
//	private void authenticate(String username ,String password) throws Exception{
//		
//		try {
//			authenticationManager.authenticate(
//			        new UsernamePasswordAuthenticationToken(username,password));
//		}catch(DisabledException e){
//			throw new Exception ("USER DISABLED" +e.getMessage());
//		}catch(BadCredentialsException e){
//			throw new Exception ("Invalid Credentials" +e.getMessage());
//		}
//	}
}

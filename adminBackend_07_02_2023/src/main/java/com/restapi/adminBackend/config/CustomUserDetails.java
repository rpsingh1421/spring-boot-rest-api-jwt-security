package com.restapi.adminBackend.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.restapi.adminBackend.entities.User;


public class CustomUserDetails implements UserDetails{
	
	private String email;
    private String password;
    private boolean status;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        email=user.getEmail();
        password=user.getPass();
//        authorities= Arrays.stream(user.getRoleCode().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
        authorities= List.of( new SimpleGrantedAuthority(user.getRoleCode()));
        status = user.getStatus();
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return status;
	}

}

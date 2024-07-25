package com.filecloud.api.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.filecloud.api.model.UserModel;

public class CustomUserDetails implements UserDetails {

	 private UserModel user;

	    public CustomUserDetails(UserModel user) {
	        this.user = user;
	    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
	    }

	    @Override
	    public String getPassword() {
	        return user.getUserPassword();
	    }

	    @Override
	    public String getUsername() {
	        return user.getUserName();
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }

}

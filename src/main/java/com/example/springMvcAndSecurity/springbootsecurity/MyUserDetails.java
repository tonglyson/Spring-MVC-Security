package com.example.springMvcAndSecurity.springbootsecurity;

import com.example.springMvcAndSecurity.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {
    private String email;
    private String password;
    private Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    public MyUserDetails(User user){
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.grantedAuthorities = user.getRoles().stream().map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toSet());
    }
    public MyUserDetails(){

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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

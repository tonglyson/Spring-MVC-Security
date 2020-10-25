package com.example.springMvcAndSecurity.springbootsecurity;

import com.example.springMvcAndSecurity.entities.User;
import com.example.springMvcAndSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User u = userRepository.findByEmail(s).orElseThrow(()-> new UsernameNotFoundException("Khong ton tai"));
        return new MyUserDetails(u);
    }
}

package com.example.spring.security.demo.service;

import com.example.spring.security.demo.dto.UserPrinciple;
import com.example.spring.security.demo.entity.User;
import com.example.spring.security.demo.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {

    private UserRepository userRepository;

    public MyUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserPrinciple(user);
    }
}

package com.example.spring.security.demo.service;

import com.example.spring.security.demo.entity.User;
import com.example.spring.security.demo.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    private JWTService jwtService;


    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager , JWTService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public java.util.List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String verify(User user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        return "Failure";
    }
}

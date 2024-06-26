package com.fintech.FintechApp.Services;

import com.fintech.FintechApp.Jwt.AuthResponse;
import com.fintech.FintechApp.Jwt.LoginRequest;
import com.fintech.FintechApp.Jwt.RegisterRequest;
import com.fintech.FintechApp.Models.Role;
import com.fintech.FintechApp.Models.User;
import com.fintech.FintechApp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    UserRepository userRepository;
    private final JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthServiceImpl(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public AuthResponse login(LoginRequest request) {
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails user = userRepository.findByName(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token).build();

    }

    public AuthResponse register(RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .lastname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .cedula(request.getCedula())
                .role(Role.USER)
                .status("Activo")
                .build();
            userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }
}

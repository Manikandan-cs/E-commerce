package com.mani.ecom.user_service.service;

import com.mani.ecom.user_service.dto.AuthResponse;
import com.mani.ecom.user_service.dto.LoginRequest;
import com.mani.ecom.user_service.dto.RegisterRequest;
import com.mani.ecom.user_service.entity.User;
import com.mani.ecom.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest req) {

        User user = User.builder()
                .username(req.getUsername())
                .email(req.getEmail())
                .password(encode(req.getPassword()))
                .role("USER")
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest req) {

        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }

    private String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    private boolean matches(String raw, String encoded) {
        return new BCryptPasswordEncoder().matches(raw, encoded);
    }
}

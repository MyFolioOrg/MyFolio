package me.may.myfolio.authentication.service.impl;

import me.may.myfolio.authentication.domain.dto.AuthenticationResult;
import me.may.myfolio.authentication.repo.UserRepository;
import me.may.myfolio.authentication.service.AuthenticationService;
import me.may.myfolio.authentication.domain.entity.User;
import me.may.myfolio.authentication.service.JwtService;
import me.may.myfolio.common.security.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationServiceImpl(UserRepository repository, JwtService jwt, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.jwtService = jwt;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthenticationResult login(String email, String password) {
        User user = repository.findByEmail(email);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return getTokens(jwtService, user);
        }
        return AuthenticationResult.INVALID;
    }

    public AuthenticationResult register(String email, String password) {
        password = passwordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.USER);
        User savedUser = repository.save(user);
        Assert.notNull(savedUser, "Failed to create user.");
        return getTokens(jwtService, savedUser);
    }
}

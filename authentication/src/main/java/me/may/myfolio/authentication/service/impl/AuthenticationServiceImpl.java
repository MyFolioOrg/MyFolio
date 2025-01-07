package me.may.myfolio.authentication.service.impl;

import me.may.myfolio.authentication.domain.dto.AuthenticationResult;
import me.may.myfolio.authentication.messaging.SuspiciousLoginEventPublisher;
import me.may.myfolio.authentication.repo.UserRepository;
import me.may.myfolio.authentication.service.AuthenticationService;
import me.may.myfolio.authentication.domain.entity.User;
import me.may.myfolio.authentication.service.JwtService;
import me.may.myfolio.authentication.service.LoginValidationService;
import me.may.myfolio.common.messaging.event.SuspiciousLoginEvent;
import me.may.myfolio.common.security.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

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
        boolean success = passwordEncoder.matches(password, user.getPassword());
        return success ? getTokens(jwtService, user) : AuthenticationResult.INVALID;
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

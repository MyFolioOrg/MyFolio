package me.may.myfolio.authentication.service.impl;

import me.may.myfolio.authentication.repo.UserRepository;
import me.may.myfolio.authentication.service.AuthenticationService;
import me.may.myfolio.authentication.domain.entity.User;
import me.may.myfolio.common.security.Role;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    public AuthenticationServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public String register(String email, String password) {
        String jwt = "some jwt";
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.USER);
        repository.save(user);
        return jwt;
    }
}

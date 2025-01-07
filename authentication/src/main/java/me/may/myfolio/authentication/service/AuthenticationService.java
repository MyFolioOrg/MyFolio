package me.may.myfolio.authentication.service;

import me.may.myfolio.authentication.domain.dto.AuthenticationResult;
import me.may.myfolio.authentication.domain.entity.User;
import me.may.myfolio.common.security.jwt.AbstractJwtService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface AuthenticationService {
    AuthenticationResult login(String email, String password);
    AuthenticationResult register(String email, String password);

    default AuthenticationResult getTokens(AbstractJwtService service, User user) {
        String access = service.generateToken(user.getEmail(), user.getId(), user.getRole(), "ACCESS");
        String refresh = service.generateToken(user.getEmail(), user.getId(), user.getRole(), "REFRESH");
        return new AuthenticationResult(access, refresh, true);
    }
}

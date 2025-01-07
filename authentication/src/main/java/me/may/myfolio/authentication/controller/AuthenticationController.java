package me.may.myfolio.authentication.controller;

import jakarta.servlet.http.HttpServletRequest;
import me.may.myfolio.authentication.domain.dto.AuthenticationResult;
import me.may.myfolio.authentication.domain.dto.Credentials;
import me.may.myfolio.authentication.messaging.SuspiciousLoginEventPublisher;
import me.may.myfolio.authentication.service.AuthenticationService;
import me.may.myfolio.authentication.service.LoginValidationService;
import me.may.myfolio.common.messaging.event.SuspiciousLoginEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Controller
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final SuspiciousLoginEventPublisher publisher;
    private final LoginValidationService validator;
    public AuthenticationController(final AuthenticationService authService, SuspiciousLoginEventPublisher publisher, LoginValidationService validator) {
        this.authService = authService;
        this.publisher = publisher;
        this.validator = validator;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResult> login(@RequestBody Credentials credentials, HttpServletRequest request) throws IOException, TimeoutException {
        String ipAddress = request.getHeader("X-Forwarded-For");
        String userAgent = request.getHeader("User-Agent");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        AuthenticationResult result = authService.login(credentials.email, credentials.password);
        if (validator.isSuspicious(credentials.email, ipAddress, "", userAgent)) {
            publisher.publish(new SuspiciousLoginEvent(credentials.email, ipAddress, userAgent, result.success()));
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResult> register(@RequestBody Credentials credentials) {
        AuthenticationResult jwt = authService.register(credentials.email, credentials.password);
        return ResponseEntity.ok(jwt);
    }
}

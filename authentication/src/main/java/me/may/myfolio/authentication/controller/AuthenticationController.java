package me.may.myfolio.authentication.controller;

import me.may.myfolio.authentication.domain.dto.AuthenticationResult;
import me.may.myfolio.authentication.domain.dto.Credentials;
import me.may.myfolio.authentication.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@Controller
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private final AuthenticationService authService;
    public AuthenticationController(final AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResult> login(@RequestBody Credentials credentials) {
        AuthenticationResult jwt = authService.login(credentials.email, credentials.password);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResult> register(@RequestBody Credentials credentials) {
        AuthenticationResult jwt = authService.register(credentials.email, credentials.password);
        return ResponseEntity.ok(jwt);
    }
}

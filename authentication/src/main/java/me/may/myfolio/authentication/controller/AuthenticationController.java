package me.may.myfolio.authentication.controller;

import me.may.myfolio.authentication.domain.dto.RegisterCredentials;
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

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterCredentials credentials) {
        String jwt = authService.register(credentials.email, credentials.password);
        return ResponseEntity.ok(jwt);
    }
}

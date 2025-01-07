package me.may.myfolio.authentication.domain.dto;

public record AuthenticationResult(String accessToken, String refreshToken, boolean success) {
    public static final AuthenticationResult INVALID = new AuthenticationResult(null, null, false);
}

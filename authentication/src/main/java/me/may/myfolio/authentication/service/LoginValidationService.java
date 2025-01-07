package me.may.myfolio.authentication.service;

public interface LoginValidationService {
    boolean isSuspicious(String email, String ip, String location, String os);
}

package me.may.myfolio.authentication.service.impl;

import me.may.myfolio.authentication.service.LoginValidationService;
import org.springframework.stereotype.Service;

@Service
public class LoginValidationServiceImpl implements LoginValidationService {
    @Override
    public boolean isSuspicious(String email, String ip, String location, String os) {
        return true;
    }
}

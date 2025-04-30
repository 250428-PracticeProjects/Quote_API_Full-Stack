package com.revature.QuoteAPI.service;

import com.revature.QuoteAPI.dto.AuthRequest;
import com.revature.QuoteAPI.model.User;

public interface AuthService {
    User register(User user);
    String login(AuthRequest authRequest);
    boolean validateToken(String token);
}
package com.doctor.app.controller;

import com.doctor.app.model.User;
import com.doctor.app.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        Map<String, String> response = authService.login(user);
        return ResponseEntity.ok(response);
    }
}


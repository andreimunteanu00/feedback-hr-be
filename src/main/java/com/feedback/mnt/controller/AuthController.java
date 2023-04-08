package com.feedback.mnt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.feedback.mnt.dto.user.UserLoginDTO;
import com.feedback.mnt.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        authService.logout();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) throws JsonProcessingException {
        return ResponseEntity.ok(authService.login(userLoginDTO));
    }

}

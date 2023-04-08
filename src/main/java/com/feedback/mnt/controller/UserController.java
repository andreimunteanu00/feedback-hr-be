package com.feedback.mnt.controller;

import com.feedback.mnt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("create/{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> createUser(@PathVariable String email) {
        userService.createUser(email);
        return ResponseEntity.noContent().build();
    }

}

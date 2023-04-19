package com.feedback.mnt.controller;

import com.feedback.mnt.dto.user.UserShowDTO;
import com.feedback.mnt.dto.user.UserUpdateDTO;
import com.feedback.mnt.service.UserService;
import com.feedback.mnt.util.Util;
import com.feedback.mnt.util.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("current-user")
    ResponseEntity<UserShowDTO> getCurrentUser() {
        return ResponseEntity.ok(userService.getUserByEmail(Util.getEmailCurrentUser()));
    }

    @GetMapping("{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<UserShowDTO> getUserByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping("create/{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> createUser(@PathVariable String email) {
        userService.createUser(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("reset-password")
    ResponseEntity<Void> resetPassword(@RequestBody UserUpdateDTO userUpdateDTO) throws UserNotFoundException {
        userService.resetPassword(userUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update")
    ResponseEntity<Void> update(@RequestBody UserUpdateDTO userUpdateDTO) {
        userService.update(userUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> deactivate(@PathVariable("email") String email) {
        userService.deactivate(email);
        return ResponseEntity.noContent().build();
    }
}

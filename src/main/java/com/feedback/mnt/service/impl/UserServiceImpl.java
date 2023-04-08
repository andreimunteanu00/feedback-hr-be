package com.feedback.mnt.service.impl;

import com.feedback.mnt.entity.User;
import com.feedback.mnt.repository.UserRepository;
import com.feedback.mnt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(String email) {
        User user = new User();
        user.setEmail(email);
        String defaultPassword = generateRandomPassword();
        user.setPassword(passwordEncoder.encode(defaultPassword));
        user.setActive(true);
        user.setResetPassword(true);
        //TODO send email with credentials + link to login in fe
        userRepository.save(user);
    }

    private static String generateRandomPassword() {
        int length = 10;
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{}|;:,.<>/?";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(randomIndex));
        }

        return password.toString();
    }
}

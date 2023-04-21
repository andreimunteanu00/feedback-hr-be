package com.feedback.mnt.service.impl;

import com.feedback.mnt.dto.mapper.UserMapper;
import com.feedback.mnt.dto.user.UserShowDTO;
import com.feedback.mnt.dto.user.UserUpdateDTO;
import com.feedback.mnt.entity.User;
import com.feedback.mnt.repository.RoleRepository;
import com.feedback.mnt.repository.UserRepository;
import com.feedback.mnt.service.MailService;
import com.feedback.mnt.service.UserService;
import com.feedback.mnt.util.Util;
import com.feedback.mnt.util.enums.EnumRole;
import com.feedback.mnt.util.exception.UserNotFoundException;
import com.feedback.mnt.util.message.Message;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final MailService mailService;

    @Override
    public void createUser(String email) {
        User user = new User();
        user.setEmail(email);
        String defaultPassword = generateRandomPassword();
        user.setPassword(passwordEncoder.encode(defaultPassword));
        user.setActive(true);
        user.setResetPassword(true);
        user.setRoles(Set.of(roleRepository.findRoleByName(EnumRole.EMPLOYEE)));
        mailService.sendEmail(user.getEmail(), "New account", "Generated password: " + defaultPassword);
        userRepository.save(user);
    }

    @Override
    public UserShowDTO getUserByEmail(String email) {
        return userMapper.toUserShowDTO(Objects.requireNonNull(userRepository.findByEmail(email).orElse(null)));
    }

    @Override
    public void update(UserUpdateDTO userUpdateDTO) {
        // TODO
    }

    @Override
    public void deactivate(String email) {
        userRepository.deactivate(email);
    }

    @Override
    @Transactional
    public void resetPassword(UserUpdateDTO userUpdateDTO) throws UserNotFoundException {
        User user = userRepository.findByEmail(Util.getEmailCurrentUser()).orElse(null);
        if (user == null) throw new UserNotFoundException(Message.USER_NOT_FOUND);
        user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        user.setResetPassword(false);
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

package com.feedback.mnt.service.impl;

import com.feedback.mnt.dto.mapper.UserMapper;
import com.feedback.mnt.dto.user.UserLoginDTO;
import com.feedback.mnt.entity.User;
import com.feedback.mnt.repository.UserRepository;
import com.feedback.mnt.service.AuthService;
import com.feedback.mnt.util.JwtTokenUtil;
import com.feedback.mnt.util.message.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByEmail(userLoginDTO.getEmail()).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format(Message.USER_NOT_FOUND_FORMATED, userLoginDTO.getEmail()));
        }
        if (!user.getEmail().equals(userLoginDTO.getEmail())
                || !passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.INVALID_CREDENTIALS);
        }
        return jwtTokenUtil.generateToken(userMapper.toUserTokenDTO(user));
    }

    @Override
    public void logout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }
}

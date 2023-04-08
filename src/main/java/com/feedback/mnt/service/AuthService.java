package com.feedback.mnt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.feedback.mnt.dto.user.UserLoginDTO;

public interface AuthService {

    String login(UserLoginDTO userLoginDTO) throws JsonProcessingException;
    void logout();

}

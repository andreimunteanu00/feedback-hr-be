package com.feedback.mnt.service;

import com.feedback.mnt.dto.user.UserShowDTO;
import com.feedback.mnt.dto.user.UserUpdateDTO;

public interface UserService {

    void createUser(String email);
    UserShowDTO getUserByEmail(String email);
    void update(UserUpdateDTO userUpdateDTO);
    void deactivate(String email);
}

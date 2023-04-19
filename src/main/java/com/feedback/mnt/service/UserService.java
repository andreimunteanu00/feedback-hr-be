package com.feedback.mnt.service;

import com.feedback.mnt.dto.user.UserShowDTO;
import com.feedback.mnt.dto.user.UserUpdateDTO;
import com.feedback.mnt.util.exception.UserNotFoundException;

public interface UserService {

    void createUser(String email);
    UserShowDTO getUserByEmail(String email);
    void update(UserUpdateDTO userUpdateDTO);
    void deactivate(String email);
    void resetPassword(UserUpdateDTO userUpdateDTO) throws UserNotFoundException;
}

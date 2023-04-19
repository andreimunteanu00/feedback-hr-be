package com.feedback.mnt.dto.user;

import com.feedback.mnt.dto.userdetail.UserDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateDTO {

    private String password;
    private UserDetailDTO userDetailDTO;

}

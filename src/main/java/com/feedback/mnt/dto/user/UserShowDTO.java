package com.feedback.mnt.dto.user;

import com.feedback.mnt.dto.userdetail.UserDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserShowDTO {

    private String email;
    private UserDetailDTO userDetail;

}

package com.feedback.mnt.dto.user;

import com.feedback.mnt.dto.role.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserTokenDTO {

    private String email;
    private Set<RoleDTO> roleSet;
}

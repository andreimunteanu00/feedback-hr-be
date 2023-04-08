package com.feedback.mnt.dto.user;

import com.feedback.mnt.dto.role.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserSecurityDTO {

    private String email;
    private Boolean active;
    private Set<RoleDTO> roleSet;
}

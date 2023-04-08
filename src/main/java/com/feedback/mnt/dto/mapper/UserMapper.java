package com.feedback.mnt.dto.mapper;

import com.feedback.mnt.dto.user.UserSecurityDTO;
import com.feedback.mnt.dto.user.UserTokenDTO;
import com.feedback.mnt.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    private final RoleMapper roleMapper;

    public UserSecurityDTO toUserSecurityDTO(User user) {
        return new UserSecurityDTO(
                user.getEmail(),
                user.getActive(),
                roleMapper.toRoleDTOSet(user.getRoles())
        );
    }

    public UserTokenDTO toUserTokenDTO(User user) {
        return new UserTokenDTO(
                user.getEmail(),
                roleMapper.toRoleDTOSet(user.getRoles())
        );
    }
}

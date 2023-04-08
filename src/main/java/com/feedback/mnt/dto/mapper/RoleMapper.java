package com.feedback.mnt.dto.mapper;

import com.feedback.mnt.dto.role.RoleDTO;
import com.feedback.mnt.entity.Role;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    RoleDTO toRoleDTO(Role role) {
        return new RoleDTO(role.getName());
    }

    Set<RoleDTO> toRoleDTOSet(Set<Role> roleSet) {
        return roleSet.stream().map(this::toRoleDTO).collect(Collectors.toSet());
    }
}

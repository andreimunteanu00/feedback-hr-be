package com.feedback.mnt.dto.mapper;

import com.feedback.mnt.dto.userdetail.UserDetailDTO;
import com.feedback.mnt.entity.UserDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDetailMapper {

    private final DepartmentMapper departmentMapper;

    UserDetailDTO toUserDetailDTO(UserDetail userDetail) {
        return new UserDetailDTO(
                userDetail.getFirstName(),
                userDetail.getLastName(),
                userDetail.getProfilePicture(),
                departmentMapper.toDepartmentDTOList(userDetail.getDepartments())
        );
    }
}

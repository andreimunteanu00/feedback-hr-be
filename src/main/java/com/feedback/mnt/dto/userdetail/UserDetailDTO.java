package com.feedback.mnt.dto.userdetail;

import com.feedback.mnt.dto.department.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailDTO {

    private String firstName;
    private String lastName;
    private byte[] profilePicture;
    private List<DepartmentDTO> departmentList;

}

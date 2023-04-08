package com.feedback.mnt.dto.mapper;

import com.feedback.mnt.dto.department.DepartmentDTO;
import com.feedback.mnt.entity.Department;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartmentMapper {

    DepartmentDTO toDepartmentDTO(Department department) {
        return new DepartmentDTO(
                department.getName()
        );
    }

    List<DepartmentDTO> toDepartmentDTOList(List<Department> departmentList) {
        return departmentList.stream().map(this::toDepartmentDTO).collect(Collectors.toList());
    }
}

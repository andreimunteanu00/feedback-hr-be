package com.feedback.mnt.repository;

import com.feedback.mnt.entity.Role;
import com.feedback.mnt.util.enums.EnumRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(EnumRole name);
}

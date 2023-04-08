package com.feedback.mnt.repository;

import com.feedback.mnt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("UPDATE User u SET u.active = (NOT u.active) WHERE u.email = :email")
    void deactivate(@Param("email") String email);
}

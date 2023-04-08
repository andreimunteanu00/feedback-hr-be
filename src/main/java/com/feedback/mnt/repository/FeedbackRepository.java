package com.feedback.mnt.repository;

import com.feedback.mnt.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("SELECT f FROM Feedback f WHERE f.user IS NOT NULL AND f.user.email = :email ORDER BY f.createdTimeStamp DESC")
    Page<Feedback> findAllByUserEmail(@Param("email") String email, Pageable page);

}

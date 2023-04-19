package com.feedback.mnt.service;

import com.feedback.mnt.dto.feedback.FeedbackDTO;
import com.feedback.mnt.dto.feedback.FeedbackListDTO;
import com.feedback.mnt.dto.feedback.FeedbackShowDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedbackService {

    void save(FeedbackDTO feedbackDTO);
    Page<FeedbackListDTO> getAllFeedback(String email, Pageable page);

    FeedbackShowDTO getFeedbackById(Long id);
}

package com.feedback.mnt.dto.mapper;

import com.feedback.mnt.dto.feedback.FeedbackListDTO;
import com.feedback.mnt.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedbackMapper {

    FeedbackListDTO toFeedbackListDTO(Feedback feedback) {
        return new FeedbackListDTO(
                feedback.getSubject(),
                feedback.getMessage(),
                feedback.getUser().getEmail(),
                feedback.getCreatedTimeStamp()
        );
    }

    public Page<FeedbackListDTO> toFeedbackListDTOPage(Page<Feedback> feedbacks) {
        List<FeedbackListDTO> aux = feedbacks.stream().map(this::toFeedbackListDTO).collect(Collectors.toList());
        return new PageImpl<>(aux);
    }
}

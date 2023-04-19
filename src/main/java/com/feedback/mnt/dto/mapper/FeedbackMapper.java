package com.feedback.mnt.dto.mapper;

import com.feedback.mnt.dto.feedback.FeedbackListDTO;
import com.feedback.mnt.dto.feedback.FeedbackShowDTO;
import com.feedback.mnt.entity.Feedback;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FeedbackMapper {

    private final UserMapper userMapper;

    FeedbackListDTO toFeedbackListDTO(Feedback feedback) {
        return new FeedbackListDTO(
                feedback.getId(),
                feedback.getSubject(),
                feedback.getMessage(),
                feedback.getUser().getEmail(),
                feedback.getCreatedTimeStamp()
        );
    }

    public FeedbackShowDTO toFeedbackShowDTO(Feedback feedback) {
        return new FeedbackShowDTO(
                feedback.getId(),
                feedback.getSubject(),
                feedback.getMessage(),
                feedback.getCreatedTimeStamp(),
                userMapper.toUserShowDTO(feedback.getUser())
        );
    }

    public Page<FeedbackListDTO> toFeedbackListDTOPage(Page<Feedback> feedbacks, Pageable page) {
        List<FeedbackListDTO> aux = feedbacks.stream().map(this::toFeedbackListDTO).collect(Collectors.toList());
        return new PageImpl<>(aux, page, feedbacks.getTotalElements());
    }
}

package com.feedback.mnt.service.impl;

import com.feedback.mnt.dto.feedback.FeedbackDTO;
import com.feedback.mnt.dto.feedback.FeedbackListDTO;
import com.feedback.mnt.dto.feedback.FeedbackShowDTO;
import com.feedback.mnt.dto.mapper.FeedbackMapper;
import com.feedback.mnt.entity.Feedback;
import com.feedback.mnt.entity.User;
import com.feedback.mnt.repository.FeedbackRepository;
import com.feedback.mnt.repository.UserRepository;
import com.feedback.mnt.service.FeedbackService;
import com.feedback.mnt.util.Util;
import com.feedback.mnt.util.message.Message;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final UserRepository userRepository;
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    @Override
    public void save(FeedbackDTO feedbackDTO) {
        feedbackRepository.save(createFeedback(feedbackDTO));
    }

    @Override
    public Page<FeedbackListDTO> getAllFeedback(String email, Pageable page) {
        return feedbackMapper.toFeedbackListDTOPage(feedbackRepository.findAllByUserEmail("%" + email + "%", page), page);
    }

    @Override
    public FeedbackShowDTO getFeedbackById(Long id) {
        return feedbackMapper.toFeedbackShowDTO(Objects.requireNonNull(feedbackRepository.findById(id).orElse(null)));
    }

    private Feedback createFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setSubject(feedbackDTO.getSubject());
        feedback.setMessage(feedbackDTO.getMessage());
        if (!feedbackDTO.getIsAnonymous()) {
            User user = userRepository.findByEmail(Util.getEmailCurrentUser()).orElse(null);
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.USER_NOT_FOUND);
            }
            feedback.setUser(user);
        }
        return feedback;
    }
}

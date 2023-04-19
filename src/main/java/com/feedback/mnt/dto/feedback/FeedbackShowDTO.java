package com.feedback.mnt.dto.feedback;

import com.feedback.mnt.dto.user.UserShowDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FeedbackShowDTO {

    private Long id;
    private String subject;
    private String message;
    private LocalDate createdTimeStamp;
    private UserShowDTO user;
}

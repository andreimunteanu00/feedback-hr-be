package com.feedback.mnt.dto.feedback;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeedbackDTO {

    private String subject;
    private String message;
    private Boolean isAnonymous;

}

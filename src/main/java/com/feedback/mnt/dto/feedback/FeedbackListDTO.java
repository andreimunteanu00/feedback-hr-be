package com.feedback.mnt.dto.feedback;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FeedbackListDTO {

    private String subject;
    private String message;
    private String userEmail;
    private LocalDate createdTimeStamp;

}

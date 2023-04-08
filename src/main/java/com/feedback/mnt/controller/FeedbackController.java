package com.feedback.mnt.controller;

import com.feedback.mnt.dto.feedback.FeedbackDTO;
import com.feedback.mnt.dto.feedback.FeedbackListDTO;
import com.feedback.mnt.service.FeedbackService;
import com.feedback.mnt.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
@AllArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping("current-user")
    ResponseEntity<Page<FeedbackListDTO>> getAllFeedbackOfCurrentUser(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                      @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(feedbackService.getAllFeedback(Util.getEmailCurrentUser(), PageRequest.of(page, size)));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Page<FeedbackListDTO>> getAllFeedback(@RequestParam(name = "email", defaultValue = "") String email,
                                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(feedbackService.getAllFeedback(email, PageRequest.of(page, size)));
    }

    @PostMapping("create")
    ResponseEntity<Void> createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.save(feedbackDTO);
        return ResponseEntity.noContent().build();
    }
}

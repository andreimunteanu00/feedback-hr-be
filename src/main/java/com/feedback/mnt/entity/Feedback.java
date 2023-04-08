package com.feedback.mnt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "FEEDBACK")
@Data
@NoArgsConstructor
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "MESSAGE")
    private String message;

    @CreationTimestamp
    @Column(name = "CREATED")
    private LocalDate createdTimeStamp;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}

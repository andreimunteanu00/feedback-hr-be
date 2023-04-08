package com.feedback.mnt.entity;

import com.feedback.mnt.util.message.Message;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "APP_USER")
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL")
    @NotBlank(message = Message.REQUIRED_EMAIL)
    @Email(message = Message.VALID_EMAIL)
    private String email;

    @Column(name = "PASSWORD")
    @NotBlank(message = Message.REQUIRED_PASSWORD)
    private String password;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "RESET_PASSWORD")
    private Boolean resetPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    private UserDetail userDetail;

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbackList;

}

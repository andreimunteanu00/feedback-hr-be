package com.feedback.mnt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USER_DETAIL")
@Data
@NoArgsConstructor
public class UserDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Lob
    @Column(name = "PROFILE_PICTURE")
    private byte[] profilePicture;

    @OneToMany(mappedBy = "userDetail")
    private List<Department> departments;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}

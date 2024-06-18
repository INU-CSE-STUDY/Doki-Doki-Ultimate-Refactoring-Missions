package com.capstone.webserver.auditor.entity;

import com.capstone.webserver.subject.entity.Subject;
import com.capstone.webserver.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="subject_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}

package com.capstone.webserver.user.entity;

import com.capstone.webserver.auditor.entity.Auditor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Schema(description = "유저 종류")
    private Role type;

    @Schema(description = "학번/교번")
    @Column(name="login_id")
    private String loginId;

    @Schema(description = "비밀번호")
    @Column
    private String password;

    @Schema(description = "이름")
    @Column
    private String name;

    @Schema(description = "전화번호")
    @Column
    private String phone;

    @Schema(description = "이메일")
    @Column
    private String email;

    @Schema(description = "학과")
    @Column
    private String department;

    @Schema(description = "성별")
    @Column
    private Gender gender;

    @Schema(description = "refresh token")
    @Column(name = "refresh_token")
    private String refreshToken;

    @Schema(description = "유저 생성 일시")
    @Column
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "user")
    private List<Auditor> auditors;
}
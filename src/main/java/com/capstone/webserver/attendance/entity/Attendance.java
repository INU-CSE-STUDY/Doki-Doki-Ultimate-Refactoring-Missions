package com.capstone.webserver.attendance.entity;

import com.capstone.webserver.nfc.entity.NFC;
import com.capstone.webserver.subject.entity.Subject;
import com.capstone.webserver.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "출석 날짜")
    @Column
    private String date;

    @Schema(description = "출석 주차")
    @Column
    private String week;

    @Schema(description = "출석 차시")
    @Column
    private String time;

    @Schema(description = "출석 상태")
    @Column
    private State state;

    @Schema(description = "출석 시작을 나타냄")
    @Column
    private LocalDateTime startTime;

    @Schema(description = "출석 끝냄을 나타냄")
    @Column
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User prof;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private NFC nfc;
}
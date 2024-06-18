package com.capstone.webserver.nfc.entity;

import com.capstone.webserver.attendance.entity.Attendance;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NFC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "nfc 기본키값")
    private Long id;

    @Column
    @Schema(description = "nfc 고유번호")
    private String nfcNumber;

    @Column
    @Schema(description = "nfc가 위치한 책상의 행 값")
    private int nfcRow;

    @Column
    @Schema(description = "nfc가 위치한 책상의 열 값")
    private int nfcCol;

    @OneToMany(mappedBy = "nfc")
    private List<Attendance> attendances;
}

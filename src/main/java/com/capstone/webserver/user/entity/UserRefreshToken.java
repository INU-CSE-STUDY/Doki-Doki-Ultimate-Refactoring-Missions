package com.capstone.webserver.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class UserRefreshToken {

    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "login_id")
    private User user;
    private String refreshToken;
    private int reissueCount = 0;

    public UserRefreshToken(User user, String refreshToken) {
        this.user = user;
        this.refreshToken = refreshToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean validateRefreshToken(String refreshToken) {
        return this.refreshToken.equals(refreshToken);
    }

    public void increaseReissueCount() {
        reissueCount++;
    }
}
